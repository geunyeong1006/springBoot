package com.cyberone.demo.batch;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cyberone.demo.model.request.rss.ReqRss;
import com.cyberone.demo.model.request.rss.ReqRssAddr;
import com.cyberone.demo.model.request.rss.ReqRssAddrList;
import com.cyberone.demo.service.RssNewsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
/**
 * RSS 수집
 */
public class RSSWriterBatchJob {
	
	private final RssNewsService rssNewsService;
	
	/**
	 * 1. RSS 주소 DB 조회
	 * 2. RSS 파싱
	 * 3. RSS 주소 DB 등록
	 * 4. RSS 주소 DB guidLast 업데이트 등록
	 * PK : Link
	 * Title: [IT&LIFE] 깔끔하게 한해를 시작하는 PC 청소 방법 
	 * Link:  http://www.ahnlab.com/kr/site/securityinfo/secunews/secuNewsView.do?curPage=1&menu_dist=2&seq=23239 
	 * description: 자신의 컴퓨터 하드웨어 틈새나 구멍 속에 붙어있는 지저분한 먼지나 찌거기들이 보이는가? PC 내부와 주변 장치에는 머리카락, 먼지, 담배 연기,
	 */
	@Scheduled(cron = "0 0 0/3 * * ?")
//	@Autowired
	public void run() {
		
		long lLastTime;
		long millis;

		lLastTime = System.currentTimeMillis();
		if (log.isDebugEnabled()) {
			log.debug("RSSWriterBatchJob Start...");
		}
		
		try {
			/* RSS 주소 DB 조회 */
			ReqRssAddrList reqRssAddrList = new ReqRssAddrList();
			reqRssAddrList.setLimit(0);
			reqRssAddrList.setRows(30);
			List<Map<String, Object>> resRssAddrList = rssNewsService.selectRssAddrList(reqRssAddrList);
			
			/* RSS 파싱 */
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			URL url = null;
			Document doc = null;
			NodeList nodes = null;
			Element element = null;
			ReqRss reqRss = null;
			
			String rssId = ""; 	// rss 츨처
			String rssSrc = ""; // rss 츨처
			String addr = ""; 	// rss xml 링크 주소
			String guidParam = ""; // guid값 파라메터명
			long guidLast = 0; 	// guid 마지막 수집 값
			
			int nodesLen = 0; 	// nodes 갯수
			int nodesLen2 = 0; 	// nodes 갯수
			
			String link = ""; 	// rss link
			String link2 = ""; 	// rss link
			String title = ""; 	// rss title
			String description = ""; // rss description
			
	    	String paramAllArr[];
	    	int paramAllArrLen;
	    	String paramArr[];
	    	String name="";
	    	String value="";
	    	long intGuid = 0; // guid 
	    	long guidMax = 0; // guid 가장 큰값
	    	boolean dbProcessBool = false;
			
			for ( Map<String, Object> result : resRssAddrList ) {
				try {
					rssId = String.valueOf(result.get("rssId"));
					rssSrc =String.valueOf(result.get("rssSrc"));
					addr = String.valueOf(result.get("rssAddr"));
					guidParam = String.valueOf(result.get("guidParam"));
					guidLast =  Long.valueOf(String.valueOf(result.get("guidLast")));
					guidMax = 0;
					
					url = new URL(addr);
					doc = builder.parse(url.openStream());
					nodes = doc.getElementsByTagName("item");
					
					nodesLen = nodes.getLength();
					nodesLen2 = nodesLen-1;
					
					int collectCnt = 0;
					
					for ( int i = 0; i < nodesLen; i++ ) {
						element = (Element) nodes.item(i);
						link = getElementValue(element, "link");
						title = StringEscapeUtils.unescapeHtml(getElementValue(element, "title"));
						description = StringEscapeUtils.unescapeHtml(getElementValue(element, "description"));
						
						/* link 에서 guidParam 값을 찾는다. */
						link2 = link.substring(link.indexOf("?")+1, link.length());
						paramAllArr = link2.split("&");
						paramAllArrLen = paramAllArr.length;
						
						for ( int j=0; j<paramAllArrLen; j++ ) {
							paramArr = paramAllArr[j].split("=");
							if ( paramArr.length > 1 ) { 
					        	name=paramArr[0];
					        	value=paramArr[1];
							} else {
					        	name=paramArr[0];
					        	value="0";
							}
							/* link 에서 guidParam 값을 찾음 */
					    	if ( name.equals(guidParam) ) {
					    		value = value.trim();
					    		break;
						   	}
						}
						intGuid = Long.parseLong(Objects.requireNonNull(value.trim(), "0"));
						
						/* guidLast 값보다 큰 guid인 rss만 등록한다. */
			    		if ( guidLast >= intGuid ) {
			    			dbProcessBool = false;
		    			} else {
		    				if ( guidMax < intGuid ) {
			    				guidMax = intGuid;
		    				}
		    				dbProcessBool = true;
		    				collectCnt++;
		    			}
			    		
			    		if ( dbProcessBool ) {
			    			description = description.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
			    			description = description.length() > 150 ? description.substring(0, 150) : description;
			    			reqRss = new ReqRss();
			    			reqRss.setRssSrc(rssSrc);
			    			reqRss.setRssLink(link);
			    			reqRss.setRegr("system");
			    			reqRss.setModr("system");
			    			reqRss.setClippingYn("n");
			    			reqRss.setBbsTit(title);
			    			reqRss.setBbsCont(description);
							/* RSS 주소 DB 등록 */
							log.debug("#### RSS insert: " + rssNewsService.addRss(reqRss));
			    		}
			    		
						/* RSS 주소 DB guidLast 업데이트 등록 */
						if ( i == nodesLen2 && guidLast < guidMax ) {
							ReqRssAddr reqRssAddr = new ReqRssAddr();
							reqRssAddr.setRssId(rssId);
							reqRssAddr.setGuidLast(String.valueOf(guidMax));
							reqRssAddr.setModr("system");
							log.debug("#### RSSAddr update: " + rssNewsService.editRssAddr(reqRssAddr));
						}
					}
					
					log.debug("#### RSSAddr last collect: " + rssNewsService.editRssAddrCollect(rssId, collectCnt));
					
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			millis = System.currentTimeMillis() - lLastTime;
			log.debug("RSSWriterBatchJob End... [소요시간: " + millis + "]");
		}
		
	}
	
    private String getElementValue(Element parent, String label) {
		return getCharacterDataFromElement((Element) parent.getElementsByTagName(label).item(0));
	}
    
	private String getCharacterDataFromElement(Element e) {
		try {
			Node child = e.getFirstChild();
			if (child instanceof CharacterData) {
				CharacterData cd = (CharacterData) child;
				return cd.getData();
			}
		} catch (Exception ex) {

		}
		return "";
	}

}


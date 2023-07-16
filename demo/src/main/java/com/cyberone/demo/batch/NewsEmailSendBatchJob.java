package com.cyberone.demo.batch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cyberone.demo.service.EmailService;
import com.cyberone.demo.service.RssNewsService;
import com.cyberone.demo.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
/**
 * 그날 뉴스가 있으면 발송
 */
public class NewsEmailSendBatchJob {
	
	private final RssNewsService rssNewsService;
	private final EmailService emailService;
	private final UsersService usersService;
	
	@Scheduled(cron = "0 0 6 * * ?")
	public void run() {
		List<Map<String, Object>> allUserList = usersService.selectAllUser();
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today =  currentDate.format(formatter);
		try {
			List<Map<String, Object>> todayNews =  rssNewsService.selectNewsList(today);
			for (Map<String, Object> user : allUserList) {
				String email = String.valueOf(user.get("email"));
				if(email != null  && !"".equals(email) & todayNews.size() > 0) {
					emailService.sendEmail(email, "[" + today + "] 보안 뉴스가 있습니다.", "보안뉴스 내용");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

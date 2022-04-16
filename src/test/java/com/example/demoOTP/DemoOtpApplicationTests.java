package com.example.demoOTP;

import com.example.demoOTP.Model.Owner;
import com.example.demoOTP.Repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@SpringBootTest
class DemoOtpApplicationTests {
	@Autowired
	OwnerRepository userRepo;

	@Test
	public void Test(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;

		Date date = new Date();
		String dt = String.valueOf(date);

		Calendar calendarE = Calendar.getInstance();
		String d = String.valueOf(calendarE.getTime().toInstant());

		String subdate = d.substring(0,10);
		String subtime = dt.substring(11 , 19);
		String[] strdate = subdate.split("-");
		String[] strtime = subtime.split(":");

		int day = Integer.parseInt(strdate[0]);
		int month = Integer.parseInt(strdate[1])-1;
		int year = Integer.parseInt(strdate[2]);
		int h = Integer.parseInt(strtime[0]);
		int m = Integer.parseInt(strtime[1])+5;
		int s = Integer.parseInt(strtime[2]);

		calendarE.set(day , month ,year , h , m , s);

		System.out.println(sdf.format(calendarE.getTime()));
		System.out.println(calendarE.getTime());

		//Add Owner
//        Owner owner = new Owner("Phiphatpong","Phiphatpong.key",new Date());
//        userRepo.save(owner);

	}

}

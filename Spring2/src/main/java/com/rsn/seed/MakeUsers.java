package com.rsn.seed;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;

import com.rsn.entity.Profile;
import com.rsn.repository.ActivatedProfileRepo;
import com.rsn.repository.ProfileRepo;
import com.rsn.service.AES;
import com.rsn.service.AES2;

public class MakeUsers {
	///// DO NOT RUN!!!!!!!!!!!!!!!
	///// DO NOT RUN!!!!!!!!!!!!!!!
	
	public static void run(ApplicationContext appContext) throws ParseException {
		// TODO Auto-generated method stub
		
		AES2 aes = new AES2();
		
		ProfileRepo profileRepo = appContext.getBean("profileRepo", ProfileRepo.class);
		
		ActivatedProfileRepo activatedRepo = appContext.getBean("activatedProfileRepo", ActivatedProfileRepo.class);
		
		ArrayList<Profile> users = new ArrayList<Profile>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
		
		users.add(new Profile("kmillson0","kmillson0@ucsd.edu","Kim","Jaquemar","Millson",new Timestamp(formatter.parse("3/25/1992").getTime()),aes.encrypt("CdY5mgnaJMpU"),"Aquamarine","Huainan","Divorced","Male","Secured fault-tolerant groupware","RmApjwatvzGfEWh","Sales Representative"));
		users.add(new Profile("ezarb1","ezarb1@noaa.gov","Eileen","Howton","Zarb",new Timestamp(formatter.parse("1/21/1923").getTime()),aes.encrypt("4c8JPKdm5kce"),"Khaki","Sydney","Married","Female","Re-engineered dynamic service-desk","KpttVQpGSciMctL","Account Representative III"));
		users.add(new Profile("aattle2","aattle2@weather.com","Arther","Baines","Attle",new Timestamp(formatter.parse("8/17/1953").getTime()),aes.encrypt("kfpve6"),"Fuscia","Caimitillo","Married","Male","Profound asymmetric paradigm","xfF0BkskbAFhM37","Web Designer I"));
		users.add(new Profile("kebhardt3","kebhardt3@gmpg.org","Ky","Synke","Ebhardt",new Timestamp(formatter.parse("6/6/1925").getTime()),aes.encrypt("lSe6xe"),"Pink","Chum Phuang","Married","Male","Digitized value-added projection","vSTVdIRzb7ey5i6","Environmental Specialist"));
		users.add(new Profile("heakly4","heakly4@squarespace.com","Harland","Abarough","Eakly",new Timestamp(formatter.parse("8/1/1945").getTime()),aes.encrypt("AmV9ASxO832"),"Indigo","Dingjiayao","Widowed","Male","Synchronised bifurcated hardware","GwiLQNtSKzGuFHq","Environmental Specialist"));
		users.add(new Profile("hsherringham5","hsherringham5@hp.com","Hadlee","Stokey","Sherringham",new Timestamp(formatter.parse("8/6/1940").getTime()),aes.encrypt("CGv4t6jN8sYe"),"Green","Nan’an","Divorced","Male","Diverse actuating encryption","CGv4t6jN8sYe","Account Representative I"));
		users.add(new Profile("iyorston6","iyorston6@wordpress.org","Ingaberg","Welds","Yorston",new Timestamp(formatter.parse("1/4/1944").getTime()),aes.encrypt("ac1kBuqv6f3"),"Goldenrod","Oullins","Married","Female","Ergonomic logistical leverage","2YCLWlMzii6LBO8","VP Product Management"));
		users.add(new Profile("lbresman7","lbresman7@addthis.com","Loralyn","Sherville","Bresman",new Timestamp(formatter.parse("7/22/1976").getTime()),aes.encrypt("6IhM5dTV"),"Yellow","Prakhon Chai","Never Married","Female","Pre-emptive dedicated application","UzlXuANifnRnoYu","Office Assistant III"));
		users.add(new Profile("hcomar8","hcomar8@hexun.com","Hoebart","Lesper","Comar",new Timestamp(formatter.parse("12/18/1928").getTime()),aes.encrypt("SFhIUd7Z"),"Maroon","Richmond","Widowed","Male","Ergonomic 24/7 superstructure","ElmuEpaZYbRaPFl","Occupational Therapist"));
		users.add(new Profile("ehedden9","ehedden9@boston.com","Ellsworth","Bowskill","Hedden",new Timestamp(formatter.parse("2/8/1924").getTime()),aes.encrypt("2dTsdf4pNM"),"Pink","Qufu","Divorced","Male","Polarised contextually-based flexibility","dtJ51ZOnrafrUvC","Safety Technician IV"));
		users.add(new Profile("cpaolettoa","cpaolettoa@netvibes.com","Clemence","Arnopp","Paoletto",new Timestamp(formatter.parse("5/10/1957").getTime()),aes.encrypt("TAlpsX8"),"Orange","Panalo-on","Divorced","Female","Re-engineered bandwidth-monitored interface","f8lW7bfwVXuZpMx","Tax Accountant"));
		users.add(new Profile("kmacfadyenb","kmacfadyenb@nbcnews.com","Krishna","Conaghy","MacFadyen",new Timestamp(formatter.parse("11/10/1979").getTime()),aes.encrypt("9vacoeKhj"),"Fuscia","Klos","Widowed","Male","Universal dedicated installation","geRqEPBkYh1VuiP","Teacher"));
		users.add(new Profile("cbarracloughc","cbarracloughc@studiopress.com","Chancey","Fetterplace","Barraclough",new Timestamp(formatter.parse("6/2/1949").getTime()),aes.encrypt("Ux6D79vGo"),"Mauv","Castelo","Never Married","Male","Compatible value-added migration","AHZFiKxvFNRVcTP","Help Desk Technician"));
		users.add(new Profile("hlewintond","hlewintond@360.cn","Harbert","Losbie","Lewinton",new Timestamp(formatter.parse("3/22/1956").getTime()),aes.encrypt("NLa7vP"),"Blue","Mazańcowice","Married","Male","Automated radical budgetary management","Nu5poelPhHsAIg5","Staff Scientist"));
		users.add(new Profile("ddelacrouxe","ddelacrouxe@springer.com","Denna","Bruck","Delacroux",new Timestamp(formatter.parse("7/30/1992").getTime()),aes.encrypt("2bRbRdvkVb"),"Red","Naguilian","Never Married","Female","Organized 5th generation artificial intelligence","jGpSDRnbQWzqeg7","Marketing Manager"));
		users.add(new Profile("eoggerf","eoggerf@reference.com","Evyn","Tangye","Ogger",new Timestamp(formatter.parse("8/29/1965").getTime()),aes.encrypt("P5ELbmb"),"Turquoise","Bohutín","Married","Male","Multi-tiered leading edge intranet","65HTCwIpfgdvHup","Desktop Support Technician"));
		users.add(new Profile("etassellg","etassellg@g.co","Edwina","Conor","Tassell",new Timestamp(formatter.parse("11/1/1945").getTime()),aes.encrypt("t3nDgwYVJzPd"),"Blue","Shuangxi","Never Married","Female","Expanded disintermediate open architecture","pRinN1Zd0opAZcv","Account Coordinator"));
		users.add(new Profile("mkadarh","mkadarh@booking.com","Matthaeus","Garnham","Kadar",new Timestamp(formatter.parse("9/14/1947").getTime()),aes.encrypt("aLUcstN0CXFP"),"Green","Krajandadapmulyo","Divorced","Male","Proactive next generation ability","dcVBOsypQmdVtL4","Structural Engineer"));
		users.add(new Profile("mswantoni","mswantoni@multiply.com","Munmro","Rolling","Swanton",new Timestamp(formatter.parse("5/17/1929").getTime()),aes.encrypt("6mewEDD"),"Fuscia","Jitian","Widowed","Male","Balanced 5th generation interface","2PhH0MlFmtjmN1M","Actuary"));
		users.add(new Profile("bcuttlerj","bcuttlerj@shareasale.com","Berty","Yaus","Cuttler",new Timestamp(formatter.parse("9/15/1997").getTime()),aes.encrypt("aXx8YT"),"Red","Portorož","Never Married","Male","Distributed bandwidth-monitored capacity","sDAm5siQeqjMu31","Graphic Designer"));
		users.add(new Profile("rraitk","rraitk@dion.ne.jp","Ruperta","Sainz","Rait",new Timestamp(formatter.parse("2/20/1941").getTime()),aes.encrypt("RSoVPvm0"),"Yellow","Khal’ch","Married","Female","Multi-layered local utilisation","GfvlQiAlcLp8UdV","Registered Nurse"));
		users.add(new Profile("branaghanl","branaghanl@symantec.com","Bertie","Kike","Ranaghan",new Timestamp(formatter.parse("3/13/1940").getTime()),aes.encrypt("czxcch2d"),"Aquamarine","Phan Thong","Married","Female","Customer-focused demand-driven standardization","YQ8Vd71YJQaC8So","Internal Auditor"));
		users.add(new Profile("jdantesiam","jdantesiam@edublogs.org","Jody","MacCostigan","Dantesia",new Timestamp(formatter.parse("3/27/1979").getTime()),aes.encrypt("aQ7TdUK8r"),"Pink","Shengmi","Divorced","Female","Customer-focused 3rd generation toolset","zG8PPU8fst95Wxu","VP Sales"));
		users.add(new Profile("blynasn","blynasn@themeforest.net","Basia","Wainman","Lynas",new Timestamp(formatter.parse("6/13/1985").getTime()),aes.encrypt("wxV3Ar"),"Khaki","Concordia","Never Married","Female","Synergistic responsive hardware","04LTA1VZWMVv6od","Marketing Manager"));
		users.add(new Profile("bbucknero","bbucknero@pen.io","Bourke","Cometto","Buckner",new Timestamp(formatter.parse("3/19/1992").getTime()),aes.encrypt("1ya8jdWRUlk"),"Violet","Yuanmen","Divorced","Male","Virtual heuristic intranet","nVZ1C6yBvjifS50","Dental Hygienist"));
		users.add(new Profile("hproschp","hproschp@pen.io","Henrieta","Whitwam","Prosch",new Timestamp(formatter.parse("3/16/1954").getTime()),aes.encrypt("kULjKlSLVr"),"Crimson","Muslyumovo","Married","Female","Multi-tiered heuristic encoding","QIw40Ayn2CJs1Dx","Professor"));
		users.add(new Profile("lbarochq","lbarochq@joomla.org","Levin","Gregoriou","Baroch",new Timestamp(formatter.parse("1/13/1923").getTime()),aes.encrypt("T2c3p9EsL"),"Violet","Kangdong-ŭp","Never Married","Male","Automated object-oriented customer loyalty","a297R11mJNIJjHj","Software Engineer I"));
		users.add(new Profile("fransomer","fransomer@meetup.com","Fanechka","Docksey","Ransome",new Timestamp(formatter.parse("12/26/1969").getTime()),aes.encrypt("1E6kMyiXZ"),"Goldenrod","Hamamatsu","Divorced","Female","Monitored global conglomeration","uA4AI8gR1U0slKb","Senior Financial Analyst"));
		users.add(new Profile("mpassos","mpassos@163.com","Malachi","Tabour","Passo",new Timestamp(formatter.parse("12/24/1995").getTime()),aes.encrypt("NcYZvqy8o2AC"),"Khaki","Angoulême","Married","Male","Operative stable firmware","8bciyqI9jkjByBf","Chief Design Engineer"));
		users.add(new Profile("bweyt","bweyt@netlog.com","Bev","Woodington","Wey",new Timestamp(formatter.parse("3/14/1999").getTime()),aes.encrypt("PJmmYgvA"),"Violet","Nowlamary","Widowed","Male","Quality-focused zero defect capacity","aTQWqkNVVFy3GZ8","Mechanical Systems Engineer"));
		
		for(Profile user : users) {
			profileRepo.insert(user);
			activatedRepo.createNotExists(user, true);
		}
		
		MakeNonUsers.run(appContext, profileRepo);
	}

}

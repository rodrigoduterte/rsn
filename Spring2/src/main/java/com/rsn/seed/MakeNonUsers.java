package com.rsn.seed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rsn.entity.PostLikes;
import com.rsn.entity.Comments;
import com.rsn.entity.CommentLikes;
import com.rsn.entity.Posts;
import com.rsn.entity.Profile;
import com.rsn.repository.CommentLikesRepo;
import com.rsn.repository.CommentRepo;
import com.rsn.repository.PostLikesRepo;
import com.rsn.repository.PostRepo;
import com.rsn.repository.ProfileRepo;

public class MakeNonUsers {

	public static void run(ApplicationContext appContext, ProfileRepo profileRepo) throws ParseException {
		// you can't call ApplicationContext twice
		//ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArrayList<Comments> comments = new ArrayList<Comments>();
		
		PostRepo postRepo = appContext.getBean("postRepo", PostRepo.class);
		PostLikesRepo postLikesRepo = appContext.getBean("postLikesRepo", PostLikesRepo.class);
		
		CommentRepo commentRepo = appContext.getBean("commentRepo", CommentRepo.class);
		CommentLikesRepo commentLikesRepo = appContext.getBean("commentLikesRepo", CommentLikesRepo.class);
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/d/YYYY hh:mm:ss a");

		////Get profiles
		Profile cpaolettoa = profileRepo.selectByUsername("cpaolettoa");
		Profile hproschp = profileRepo.selectByUsername("hproschp");
		Profile mkadarh = profileRepo.selectByUsername("mkadarh");
		Profile eoggerf = profileRepo.selectByUsername("eoggerf");
		Profile mswantoni = profileRepo.selectByUsername("mswantoni");
		Profile ddelacrouxe = profileRepo.selectByUsername("ddelacrouxe");
		Profile heakly4 = profileRepo.selectByUsername("heakly4");
		Profile hcomar8 = profileRepo.selectByUsername("hcomar8");
		Profile jdantesiam = profileRepo.selectByUsername("jdantesiam");
		Profile hlewintond = profileRepo.selectByUsername("hlewintond");
		Profile bbucknero = profileRepo.selectByUsername("bbucknero");
		Profile etassellg = profileRepo.selectByUsername("etassellg");
		Profile kebhardt3 = profileRepo.selectByUsername("kebhardt3");
		Profile aattle2 = profileRepo.selectByUsername("aattle2"); // ehedden9
		Profile ehedden9 = profileRepo.selectByUsername("ehedden9");
		Profile blynasn = profileRepo.selectByUsername("blynasn");
		Profile hsherringham5 = profileRepo.selectByUsername("hsherringham5");
		Profile bweyt = profileRepo.selectByUsername("bweyt");
		Profile lbresman7 = profileRepo.selectByUsername("lbresman7");
		Profile cbarracloughc = profileRepo.selectByUsername("cbarracloughc");
		Profile lbarochq = profileRepo.selectByUsername("lbarochq");
		Profile fransomer = profileRepo.selectByUsername("fransomer");
		Profile ezarb1 = profileRepo.selectByUsername("ezarb1");

		/// Create Posts
		Posts post1 = new Posts(formatter.parse("01/02/2019 12:49:31 AM"), "", 
				"iterate global e-services", cpaolettoa);
		postRepo.insert(post1);
		
		Posts post2 = new Posts(formatter.parse("01/03/2019 08:17:09 AM"), "", 
				"engage strategic paradigms", cpaolettoa);
		postRepo.insert(post2);
		
		Posts post3 = new Posts(formatter.parse("01/03/2019 11:37:45 PM"), "", 
				"repurpose viral convergence", etassellg);
		postRepo.insert(post3);
		
		Posts post4 = new Posts(formatter.parse("01/04/2019 01:36:16 PM"), "", 
				"recontextualize synergistic interfaces", ehedden9);
		postRepo.insert(post4);
		
		Posts post5 = new Posts(formatter.parse("01/06/2019 03:28:07 AM"), "", 
				"productize synergistic architectures", ddelacrouxe);
		postRepo.insert(post5);
		
		//// Create Post Likes
		postLikesRepo.insert(new PostLikes(cpaolettoa, post1));
		postLikesRepo.insert(new PostLikes(mkadarh, post2));
		postLikesRepo.insert(new PostLikes(mswantoni, post2));
		postLikesRepo.insert(new PostLikes(ddelacrouxe, post2));
		postLikesRepo.insert(new PostLikes(heakly4, post2));
		postLikesRepo.insert(new PostLikes(eoggerf, post3));
		postLikesRepo.insert(new PostLikes(hcomar8, post3));
		postLikesRepo.insert(new PostLikes(jdantesiam, post3));
		postLikesRepo.insert(new PostLikes(hproschp, post3));
		postLikesRepo.insert(new PostLikes(hlewintond, post4));
		postLikesRepo.insert(new PostLikes(hcomar8, post4));
		postLikesRepo.insert(new PostLikes(bbucknero, post4));
		postLikesRepo.insert(new PostLikes(hlewintond, post4));
		postLikesRepo.insert(new PostLikes(etassellg, post5));
		postLikesRepo.insert(new PostLikes(kebhardt3, post5));
		postLikesRepo.insert(new PostLikes(aattle2, post5));
		postLikesRepo.insert(new PostLikes(ehedden9, post5));
		
		//// Create comments
		Comments comment1 = new Comments(formatter.parse("01/03/2019 12:02:06 PM"),"In congue.",
				blynasn, post1
		);
		Comments comment2 = new Comments(formatter.parse("01/04/2019 10:48:15 AM"),
				"Curabitur convallis.",jdantesiam, post2
		);
		Comments comment3 = new Comments(formatter.parse("01/04/2019 03:33:24 PM"),
				"Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat.",
				blynasn, post3
		);
        Comments comment4 = new Comments(formatter.parse("01/05/2019 06:52:54 AM"),
        		"Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; "
        		+ "Donec pharetra, magna vestibulum aliquet ultrices",bbucknero, post4
        );
		Comments comment5 = new Comments(formatter.parse("01/05/2019 09:53:54 AM"),"Aenean auctor gravida sem. "
				+ "Praesent id massa id nisl venenatis lacinia.",hlewintond, post5
		);
		
		comments.add(comment1); comments.add(comment2); comments.add(comment3); 
		comments.add(comment4); comments.add(comment5);

		for (Comments comment : comments) {
			commentRepo.insert(comment);
		}
		
		//comment likes
		commentLikesRepo.insert(new CommentLikes(hsherringham5, comment1));
		commentLikesRepo.insert(new CommentLikes(bweyt, comment1));
		commentLikesRepo.insert(new CommentLikes(lbresman7, comment2));
		commentLikesRepo.insert(new CommentLikes(cbarracloughc, comment2));
		commentLikesRepo.insert(new CommentLikes(bbucknero, comment2));
		commentLikesRepo.insert(new CommentLikes(eoggerf, comment2));
		commentLikesRepo.insert(new CommentLikes(lbarochq, comment3));
		commentLikesRepo.insert(new CommentLikes(fransomer, comment3));
		commentLikesRepo.insert(new CommentLikes(ezarb1, comment3));
		commentLikesRepo.insert(new CommentLikes(jdantesiam, comment3));
		commentLikesRepo.insert(new CommentLikes(blynasn, comment4));
		commentLikesRepo.insert(new CommentLikes(jdantesiam, comment5));
		commentLikesRepo.insert(new CommentLikes(hsherringham5, comment5));
	}
}

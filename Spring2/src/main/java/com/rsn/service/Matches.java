package com.rsn.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matches {
	
	
	public static int matchedNums (String pat, String toMatch) {
		Pattern p = Pattern.compile(pat);
		Matcher m = p.matcher(toMatch);
		int count = 0;
		while (m.find())
		    count++;
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(matchedNums("bucketgame", "https://bucketgame.s3.us-east-2.amazonaws.com/https%3A/%2Fbucketgame.s3.us-east-2.amazonaws.com/"));
	}
	
}

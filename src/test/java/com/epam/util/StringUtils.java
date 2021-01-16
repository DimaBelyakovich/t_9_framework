package com.epam.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StringUtils
{
	private static final String ALFANUMERICAL_ALL_CAPS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random random = new Random();

	public static String getRandomString(int stringLength)
	{
		StringBuilder stringBuilder = new StringBuilder(stringLength);
		for (int i = 0; i < stringLength; i++)
		{
			stringBuilder.append(ALFANUMERICAL_ALL_CAPS.charAt(random.nextInt(ALFANUMERICAL_ALL_CAPS.length())));
		}
		return stringBuilder.toString();
	}

	public static Map<String, String> getLocaleAndFormat(){
		Map<String, String> localAndFormat = new HashMap<>();

		localAndFormat.put("Минск", "б.p.");
		localAndFormat.put("Москва", "₽");
		localAndFormat.put("Нур-Султан", "тнг");

		return localAndFormat;
	}
}

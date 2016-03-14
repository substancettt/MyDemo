package com.wellshang.mytools;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wellshang.utils.DESCryptUtil;

public class DESCryptTest {

	@BeforeClass
	public static void setUp() {
		System.out.println("setUp");
	}

	@Test
	public void cryptTest() {
		String orgContent = "ABCDEFGxyz1234567890~<>?&";
		String enContent = DESCryptUtil.enCrypt(orgContent);
		String deContent = DESCryptUtil.deCrypt(enContent);

		System.out.println("Original text: " + orgContent);
		System.out.println("Encrypt text: " + enContent);
		System.out.println("Decrypt text: " + deContent);
	}

	@AfterClass
	public static void tearDown() {
		System.out.println("tearDown");
	}
}

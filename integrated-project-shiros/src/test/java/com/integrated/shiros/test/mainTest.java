package com.integrated.shiros.test;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * ClassName: mainTest
 * Description:
 * Author: liangchao
 * Date: 2018/7/21 2:02
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class mainTest {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        Md5Hash md5Hash = new Md5Hash("123456","admin");
        System.out.println(md5Hash.toString());
    }
}

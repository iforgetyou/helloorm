package com.zy17.protobuf.domain;


import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-23
 * Time: 下午3:57
 */
public class EngTest {
    @Test
    public void cardTest(){
        Eng.Card card = Eng.Card.newBuilder().setChiText("1111").setEngText("abc").build();
        Eng.Card card1 = card.toBuilder().setChiText("222").build();
        System.out.println(card);
        System.out.println(card1);
    }
}

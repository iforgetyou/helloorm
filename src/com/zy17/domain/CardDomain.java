package com.zy17.domain;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zy17.protobuf.domain.Eng;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-17
 * Time: 上午9:33
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class CardDomain extends Base {
    private Eng.Card card;
    @Persistent
    private String imageUrl;
    private byte[] bytes;

    public CardDomain(Eng.Card card) {
        this.setCard(card);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Eng.Card getCard() {
        try {
            this.card = Eng.Card.parseFrom(this.bytes);
        } catch (InvalidProtocolBufferException e) {
            logger.error("ProtocolBuffer反序列化失败", e);
        }
        return card;
    }

    public void setCard(Eng.Card card) {
        this.bytes= card.toByteArray();
        this.card = card;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}

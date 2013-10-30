package com.zy17.domain;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zy17.protobuf.domain.Eng;

import javax.jdo.annotations.*;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-17
 * Time: 上午9:33
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class CardDomain extends Base {
    @NotPersistent
    private Eng.Card card;
    @Persistent
    private byte[] bytes;
    @Persistent
    private boolean imageFlag;
    @Persistent
    private boolean soundFlag;

    public CardDomain(Eng.Card card) {
        this.setCard(card);
        this.setImageFlag(card.getImage() == null);
        this.setSoundFlag(card.getSound() == null);
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
        this.bytes = card.toByteArray();
        this.card = card;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public boolean isImageFlag() {
        return imageFlag;
    }

    public void setImageFlag(boolean imageFlag) {
        this.imageFlag = imageFlag;
    }

    public boolean isSoundFlag() {
        return soundFlag;
    }

    public void setSoundFlag(boolean soundFlag) {
        this.soundFlag = soundFlag;
    }
}

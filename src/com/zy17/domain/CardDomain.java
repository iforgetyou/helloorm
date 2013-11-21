package com.zy17.domain;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zy17.protobuf.domain.Eng;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-17
 * Time: 上午9:33
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class CardDomain extends Base {
    @Persistent
    private String engText;
    @Persistent
    private String chiText;
    @Persistent
    private byte[] imageBytes;
    @Persistent
    private byte[] soundBytes;

    public CardDomain(Eng.Card card) {
        this.engText = card.getEngText();
        // gae string use unicode ,but this is utf-8 todo
        this.chiText = card.getChiText();
        this.imageBytes = card.getImage().toByteArray();
        this.soundBytes = card.getSound().toByteArray();
    }

    public Eng.Card getCard() {
        try {
            Eng.Card.Builder builder = Eng.Card.newBuilder();
            builder
                    .setEngText(this.engText)
                    .setChiText(new String(this.chiText.getBytes("unicode"), "utf-8"));
            if (imageBytes != null) {
                builder.setImage(Eng.PbImage.parseFrom(imageBytes));
            }
            if (soundBytes != null) {
                builder.setSound(Eng.PbSound.parseFrom(soundBytes));
            }
            return builder.build();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


}

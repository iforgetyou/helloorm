package com.zy17.domain;

import com.google.protobuf.GeneratedMessage;
import com.zy17.protobuf.domain.AddressBookProtos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jdo.annotations.*;
import java.io.Serializable;
import java.util.Date;

@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.SUBCLASS_TABLE)
public class Base implements Serializable {
    final static Logger logger = LoggerFactory
            .getLogger(Base.class);

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    private String encodedKey;

//    @Persistent
//    @Extension(vendorName="datanucleus", key="gae.pk-name", value="true")
//    private String keyName;

    @Persistent
    private Date createdAt;
    @Persistent
    private Date updatedAt;

    public Base() {
    }

    public String getEncodedKey() {
        return encodedKey;
    }

    public void setEncodedKey(String encodedKey) {
        this.encodedKey = encodedKey;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}

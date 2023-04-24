package com.dts.intechweb.model;

import lombok.Data;
import lombok.Setter;
import java.util.Base64;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User author;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] fileData;
    private String content;
    
    private MessageType type;
    private String fileName;
    private String filetype;
    // private String fileData;

  



    public enum MessageType {
        JOIN, LEAVE, CHAT, FILE
    }


      // getter and setter for fileName field
      public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

      // getter and setter for fileName field
      public String getFileType() {
        return filetype;
    }

    public void setFileType(String filetype) {
        this.filetype = filetype;
    }

    // public String getFileData() {
    //     return fileData;
    // }

    // public void setFileData(byte[] fileData) {
    //     this.fileData = Base64.getEncoder().encodeToString(fileData);
    // }
    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}

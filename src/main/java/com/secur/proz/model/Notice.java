package com.secur.proz.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Date;

@Entity
@Table(name = "notice_details")
@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "notice_id")
  private int noticeId;

  @Column(name = "notice_summary")
  private String noticeSummary;

  @Column(name = "notice_details")
  private String noticeDetails;

  @Column(name = "notic_beg_dt")
  private Date noticBegDt;

  @Column(name = "notic_end_dt")
  private Date noticEndDt;

  @Column(name = "create_dt")
  private Date createDt;

  @Column(name = "update_dt")
  private Date updateDt;

  public Notice(String noticeSummary, String noticeDetails, Date noticBegDt, Date noticEndDt, Date createDt, Date updateDt) {
    this.noticeSummary = noticeSummary;
    this.noticeDetails = noticeDetails;
    this.noticBegDt = noticBegDt;
    this.noticEndDt = noticEndDt;
    this.createDt = createDt;
    this.updateDt = updateDt;
  }
}

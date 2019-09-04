package com.llzw.apigate.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

@Indexed
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Product extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  protected Long id;

  @Column(nullable = false)
  protected String name;

  @Lob
  protected String introduction;

  @Column(nullable = false)
  protected float price;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  protected Date usedAt;

  protected boolean valid = true;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bundleId")
  protected Bundle bundle;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customerId")
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
  @JsonIdentityReference(alwaysAsId = true)
  protected User customer;
}

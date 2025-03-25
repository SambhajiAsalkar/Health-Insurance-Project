package com.nt.model.promotions;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name="multi_db_offers")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Offers {
	@Id
	@SequenceGenerator(name="gen1",sequenceName = "offers_sequence",initialValue = 10000,allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
private Integer offerId;
	@NonNull
	@Column(length=20)
private String offerName;
	@NonNull
	@Column(length=20)
private String offerCode;
	@NonNull
	@Column(name = "duscountPercentage", columnDefinition = "NUMBER")
private Float duscountPercentage;
	@NonNull
	@Column(name = "expDate", columnDefinition = "TIMESTAMP(6)")
private LocalDateTime expDate;
}

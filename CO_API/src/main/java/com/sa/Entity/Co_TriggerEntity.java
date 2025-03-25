package com.sa.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Co_Trigger")
@Data
public class Co_TriggerEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trgId;
	private Long caseNum;
    @Lob
    private byte[] co_pdf;
    private String trgStatus;
    
}

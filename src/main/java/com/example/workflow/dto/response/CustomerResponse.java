package com.example.workflow.dto.response;

import com.example.workflow.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
        private long id;
        private String name;
        private String surname;
        private String fullName;
        private String fin;
        private Date createdDate;
        private Integer active;
        private Type type;
        }

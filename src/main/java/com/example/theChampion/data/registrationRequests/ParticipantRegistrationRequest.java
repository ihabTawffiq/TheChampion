package com.example.theChampion.data.registrationRequests;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ParticipantRegistrationRequest {

    private String firstName;
    private String lastName;
    private String department;
    private String phone;
}

package com.croquiscom.recruit.vacation.application;

import com.croquiscom.recruit.vacation.dto.VacationRequest;
import com.croquiscom.recruit.vacation.dto.VacationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("vacation service test")
@SpringBootTest
public class VacationServiceTest {

    @Autowired
    private VacationService vacationService;

    @DisplayName("create vacation")
    @Test
    public void createVacation() {
        VacationRequest request = new VacationRequest();
        request.setVacationType("whole");
        request.setVacationStartDate(LocalDate.of(2021, 6, 1));
        request.setVacationEndDate(LocalDate.of(2021, 6, 4));
        request.setUsedDays(4);
        request.setComment("asdf");
        VacationResponse actual = vacationService.createVacation("user", request);
        assertThat(actual.getVacationId()).isNotNull();
        assertThat(actual.getRemainingUsedDays()).isEqualTo(11);
    }

    @DisplayName("cancel vacation")
    @Test
    public void cancelVacation() {
        createVacation();
        VacationResponse actual = vacationService.cancelVacation("user", 1L);
        assertThat(actual.getRemainingUsedDays()).isEqualTo(15);
    }

}

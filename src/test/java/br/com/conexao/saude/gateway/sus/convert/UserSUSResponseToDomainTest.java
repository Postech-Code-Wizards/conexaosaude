package br.com.conexao.saude.gateway.sus.convert;

import br.com.conexao.saude.domain.UserSUS;
import br.com.conexao.saude.gateway.sus.response.UserSUSResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserSUSResponseToDomainTest {

    @InjectMocks
    private UserSUSResponseToDomain userSUSResponseToDomain;

    @Test
    @DisplayName("Should correctly convert UserSUSResponse to UserSUS domain object with all fields populated")
    void shouldCorrectlyConvertAllFieldsPopulated() {
        UserSUSResponse userSUSResponse = Instancio.create(UserSUSResponse.class);

        UserSUS userSUS = userSUSResponseToDomain.execute(userSUSResponse);

        assertNotNull(userSUS);
        assertEquals(userSUSResponse.id(), userSUS.getId());
        assertEquals(userSUSResponse.name(), userSUS.getName());
        assertEquals(userSUSResponse.cpf(), userSUS.getCpf());
        assertEquals(userSUSResponse.age(), userSUS.getAge());
        assertEquals(userSUSResponse.gender(), userSUS.getGender());
        assertEquals(userSUSResponse.weight(), userSUS.getWeight());
        assertEquals(userSUSResponse.height(), userSUS.getHeight());
        assertEquals(userSUSResponse.bmi(), userSUS.getBmi());
        assertEquals(userSUSResponse.medicalConditions(), userSUS.getMedicalConditions());
        assertEquals(userSUSResponse.allergies(), userSUS.getAllergies());
    }

    @Test
    @DisplayName("Should correctly convert all fields when Instancio generates diverse data types")
    void shouldCorrectlyConvertWithDiverseData() {
        UserSUSResponse userSUSResponse = Instancio.of(UserSUSResponse.class)
                .generate(field(UserSUSResponse::age), gen -> gen.ints().range(18, 99))
                .generate(field(UserSUSResponse::weight), gen -> gen.doubles().range(40.0, 150.0))
                .generate(field(UserSUSResponse::height), gen -> gen.doubles().range(1.40, 2.10))
                .create();

        UserSUS userSUS = userSUSResponseToDomain.execute(userSUSResponse);

        assertNotNull(userSUS);
        assertEquals(userSUSResponse.id(), userSUS.getId());
        assertEquals(userSUSResponse.age(), userSUS.getAge());
        assertEquals(userSUSResponse.weight(), userSUS.getWeight());
        assertEquals(userSUSResponse.height(), userSUS.getHeight());
    }
}
package br.com.conexao.saude.gateway.sus;

import br.com.conexao.saude.domain.UserSUS;
import br.com.conexao.saude.gateway.UserInfoSUSGateway;
import br.com.conexao.saude.gateway.sus.convert.UserSUSResponseToDomain;
import br.com.conexao.saude.gateway.sus.response.UserSUSResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class UserInMemoryDataAdapter implements UserInfoSUSGateway {

    private final UserSUSResponseToDomain userSUSResponseToDomain;

    private static final String DIABETES_TIPO_2_HIPERTENSAO = "Diabetes tipo 2, Hipertensão";
    private static final String LACTOSE = "Lactose";
    private static final String HIPERTENSAO_COLESTEROL_ALTO = "Hipertensão, Colesterol alto";
    private static final String GLUTEN = "Glúten";

    private static final Map<String, UserSUSResponse> userSUSResponse = Stream.of(
            new UserSUSResponse(1L, "João Pereira", "11122233344", 72, "M", 78.00, 1.68, 27.64, DIABETES_TIPO_2_HIPERTENSAO, LACTOSE, "+5511999000111"),
            new UserSUSResponse(2L, "Maria Oliveira", "22233344455", 68, "F", 62.00, 1.55, 25.81, HIPERTENSAO_COLESTEROL_ALTO, GLUTEN, "+5511999000222"),
            new UserSUSResponse(3L, "Antônio Souza", "33344455566", 80, "M", 85.00, 1.70, 29.41, "Diabetes tipo 2, Obesidade", "Amendoim", "+5511999000333"),
            new UserSUSResponse(4L, "Helena Santos", "44455566677", 75, "F", 70.00, 1.60, 27.34, "Colesterol alto, Anemia", LACTOSE, "+5511999000444"),
            new UserSUSResponse(5L, "Carlos Lima", "55566677788", 69, "M", 90.00, 1.72, 30.42, "Obesidade, Hipertensão", null, "+5511999000555"),
            new UserSUSResponse(6L, "Ana Rodrigues", "66677788899", 82, "F", 54.00, 1.58, 21.63, "Doença celíaca, Anemia", GLUTEN, "+5511999000666"),
            new UserSUSResponse(7L, "Francisco Alves", "77788899900", 77, "M", 72.00, 1.66, 26.13, "Hipertensão", "Frutos do mar", "+5511999000777"),
            new UserSUSResponse(8L, "Sonia Ferreira", "88899900011", 73, "F", 68.00, 1.62, 25.91, DIABETES_TIPO_2_HIPERTENSAO, null, "+5511999000888"),
            new UserSUSResponse(9L, "Raimundo Gomes", "99900011122", 85, "M", 76.00, 1.69, 26.61, "Insuficiência renal leve, Anemia", "Penicilina", "+5511999000999"),
            new UserSUSResponse(10L, "Beatriz Martins", "12312312312", 90, "F", 60.00, 1.54, 25.30, HIPERTENSAO_COLESTEROL_ALTO, null, "+5511999001010"),
            new UserSUSResponse(11L, "Luiz Barbosa", "23423423423", 65, "M", 82.00, 1.73, 27.40, "Diabetes tipo 2", LACTOSE, "+5511999001111"),
            new UserSUSResponse(12L, "Cecília Duarte", "34534534534", 78, "F", 66.00, 1.59, 26.11, "Obesidade, Diabetes tipo 2", null, "+5511999001222"),
            new UserSUSResponse(13L, "Marcelo Pinto", "45645645645", 71, "M", 88.00, 1.75, 28.73, HIPERTENSAO_COLESTEROL_ALTO, "Amendoim", "+5511999001333"),
            new UserSUSResponse(14L, "Laura Nunes", "56756756756", 84, "F", 58.00, 1.56, 23.83, "Anemia, Insuficiência renal leve", null, "+5511999001444"),
            new UserSUSResponse(15L, "Emanuel Rocha", "67867867867", 79, "M", 74.00, 1.68, 26.22, "Diabetes tipo 2, Colesterol alto", GLUTEN, "+5511999001555"),
            new UserSUSResponse(16L, "Irene Castro", "78978978978", 67, "F", 64.00, 1.60, 25.00, "Hipertensão", LACTOSE, "+5511999001666"),
            new UserSUSResponse(17L, "Roberto Mendes", "89089089089", 86, "M", 80.00, 1.70, 27.68, "Obesidade, Diabetes tipo 2", null, "+5511999001777"),
            new UserSUSResponse(18L, "Patrícia Rocha", "90190190190", 70, "F", 72.00, 1.65, 26.45, "Colesterol alto", "Nozes", "+5511999001888"),
            new UserSUSResponse(19L, "Nelson Cardoso", "01201201201", 88, "M", 68.00, 1.67, 24.38, "Anemia", null, "+5511999001999"),
            new UserSUSResponse(20L, "Dora Azevedo", "21321321321", 76, "F", 71.00, 1.63, 26.72, DIABETES_TIPO_2_HIPERTENSAO, "Frutos do mar", "+5511999002000")
    ).collect(Collectors.collectingAndThen(
            Collectors.toMap(UserSUSResponse::cpf, user -> user),
            Collections::unmodifiableMap
    ));

    @Override
    public Optional<UserSUS> findByCpf(String cpf) {
        Optional<UserSUSResponse> userSUSResponseOptional = Optional.ofNullable(userSUSResponse.get(cpf));
        return userSUSResponseOptional.map(userSUSResponseToDomain::execute);
    }

}
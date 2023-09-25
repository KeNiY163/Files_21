package guru.qa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Cars;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParseTest {

    @Test
    @DisplayName(".json test")
    public void testJsonParsing() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("src/test/resources/cars_feed.json");
        List<Cars> carsGet = objectMapper.readValue(jsonFile, new TypeReference<>() {});

        Assertions.assertThat(carsGet).hasSize(4);

        Cars firstCar = carsGet.get(0);
        Assertions.assertThat(firstCar.getId()).isEqualTo(1);
        Assertions.assertThat(firstCar.getBrand()).isEqualTo("BMW");
        Assertions.assertThat(firstCar.getModel()).isEqualTo("X3");
        Assertions.assertThat(firstCar.getCountry()).isEqualTo("German");
        Assertions.assertThat(firstCar.getYear()).isEqualTo(2017);
        Assertions.assertThat(firstCar.getPrice()).isEqualTo(4572114);

        Cars secondCar = carsGet.get(1);
        Assertions.assertThat(secondCar.getId()).isEqualTo(2);
        Assertions.assertThat(secondCar.getBrand()).isEqualTo("Audi");
        Assertions.assertThat(secondCar.getModel()).isEqualTo("R7");
        Assertions.assertThat(secondCar.getCountry()).isEqualTo("England");
        Assertions.assertThat(secondCar.getYear()).isEqualTo(2019);
        Assertions.assertThat(secondCar.getPrice()).isEqualTo(5411667);

    }
}

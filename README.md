Aqui está o template de README para o seu projeto **WeatherApp** com uma descrição detalhada do projeto, estrutura de pastas, e as tecnologias utilizadas.

---

# WeatherApp

**WeatherApp** é uma aplicação web que permite aos usuários consultar a previsão do tempo de uma cidade. A aplicação faz requisições à API do OpenWeatherMap e exibe informações como a temperatura e a condição climática.

## 🚀 Tecnologias Utilizadas

* **Spring Boot**: Framework Java para construir a aplicação de forma rápida e simples.
* **Thymeleaf**: Motor de template para gerar HTML dinâmico.
* **RestTemplate**: Para realizar requisições HTTP à API do OpenWeatherMap.
* **OpenWeatherMap API**: Para buscar dados sobre o clima.
* **Maven**: Gerenciador de dependências e build do projeto.

## 📦 Estrutura do Projeto

A estrutura do seu projeto **WeatherApp** é organizada da seguinte forma:

```
WeatherApp/
│
├── .gitignore              # Arquivo que lista os arquivos que o Git deve ignorar
├── pom.xml                 # Arquivo de configuração do Maven
│
├── .idea                   # Arquivos de configuração do IntelliJ IDEA
│
├── .mvn                    # Arquivos de configuração do Maven Wrapper
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── weatherapp/
│   │   │               ├── WeatherAppApplication.java       # Classe principal de inicialização da aplicação
│   │   │               ├── controller/
│   │   │               │   └── WeatherRestController.java   # Controlador que gerencia as requisições e exibe os dados
│   │   │               ├── model/
│   │   │               │   └── WeatherResponse.java         # Classe de resposta da API com dados do clima
│   │   │               └── service/
│   │   │                   └── WeatherService.java         # Serviço que comunica com a API e processa os dados
│   │   ├── resources/
│   │   │   ├── application.properties                      # Propriedades da aplicação, incluindo chave da API
│   │   │   └── templates/
│   │   │       └── weather.html                            # Template HTML para exibir o clima
│   └── test/                                              # Testes unitários
├── target/               # Arquivos gerados após o build
└── README.md             # Documentação do projeto
```

## 🛠️ Funcionalidades

* **Consulta de clima**: Permite ao usuário buscar a previsão do tempo por cidade.
* **Exibição de informações**: Exibe a temperatura e as condições climáticas.
* **Interface simples**: A interface da aplicação foi criada com o Thymeleaf, um motor de templates para facilitar a renderização de dados dinâmicos.

## ⚙️ Como Rodar o Projeto

Para rodar o projeto localmente, siga os passos abaixo:

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/nswia/meteor_ia.git
   cd meteor_ia
   ```

2. **Configure o arquivo `application.properties`**:

   * Acesse [OpenWeatherMap](https://openweathermap.org/api) e crie uma conta para obter uma chave de API (API Key).
   * No arquivo `src/main/resources/application.properties`, substitua o valor de `weather.api.key` pela sua chave de API:

     ```properties
     weather.api.key=SUA_CHAVE_DE_API
     ```

3. **Compile e rode a aplicação**:
   Se você tem o Maven instalado:

   ```bash
   mvn spring-boot:run
   ```

   Ou, se você usa o IntelliJ IDEA, basta executar a classe `WeatherAppApplication.java`.

4. **Acesse a aplicação**:

   * Abra o navegador e acesse: [http://localhost:8080/weather](http://localhost:8080/weather)
   * Você pode consultar o clima de qualquer cidade, como por exemplo, "São Paulo".

## 🌐 Acessar a API do OpenWeatherMap

A aplicação usa a seguinte URL para consultar os dados do clima:

```
https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric&lang=pt
```

A chave de API (API Key) é necessária para autenticar a requisição.

## 🔧 Detalhes de Implementação

* **Controlador (`WeatherRestController`)**:
  Responsável por lidar com as requisições para o endpoint `/weather`. Ele recebe o nome da cidade via parâmetros de consulta (query parameters), consulta os dados do clima e os passa para a view (template HTML).

  ```java
  @Controller
  public class WeatherRestController {

      @Autowired
      private WeatherService weatherService;

      @GetMapping("/weather")
      public String getWeather(@RequestParam(name = "city", required = false, defaultValue = "São Paulo") String city, Model model) {
          WeatherResponse weatherData = weatherService.getWeatherData(city, "SUA_CHAVE_DE_API");
          model.addAttribute("weatherData", weatherData);
          model.addAttribute("city", city);
          return "weather";
      }
  }
  ```

* **Serviço (`WeatherService`)**:
  O serviço é responsável por comunicar-se com a API externa do OpenWeatherMap usando o `RestTemplate`.

  ```java
  @Service
  public class WeatherService {
      private static final String WEATHER_API_URL =
              "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric&lang=pt";

      public WeatherResponse getWeatherData(String city, String apiKey) {
          RestTemplate restTemplate = new RestTemplate();
          return restTemplate.getForObject(WEATHER_API_URL, WeatherResponse.class, city, apiKey);
      }
  }
  ```

* **Modelo (`WeatherResponse`)**:
  A classe `WeatherResponse` mapeia a resposta da API, armazenando dados sobre temperatura e condição climática.

  ```java
  public class WeatherResponse {
      private Main main;
      private List<Weather> weather;

      // getters e setters
  }
  ```

* **Template HTML (`weather.html`)**:
  Utiliza Thymeleaf para renderizar dinamicamente os dados climáticos no frontend.

  ```html
  <form action="/weather" method="get">
      <label for="city">Cidade:</label>
      <input type="text" id="city" name="city" th:value="${city}" required>
      <button type="submit">Buscar</button>
  </form>

  <div th:if="${weatherData != null}">
      <h2>Clima em <span th:text="${city}">Cidade</span>:</h2>
      <p>Temperatura: <span th:text="${weatherData.temp}">0</span> °C</p>
      <p>Condição: <span th:text="${weatherData.condition}">Indisponível</span></p>
  </div>
  ```

## 📄 Licença

Este projeto é licenciado sob a [MIT License](LICENSE).

---


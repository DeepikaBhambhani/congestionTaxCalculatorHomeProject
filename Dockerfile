FROM openjdk:11
EXPOSE 8080
ADD target/congestion-tax-calculator.jar congestion-tax-calculator-docker.jar
ENTRYPOINT ["java","-jar","congestion-tax-calculator-docker.jar"]
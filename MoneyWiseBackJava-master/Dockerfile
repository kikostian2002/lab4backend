FROM public.ecr.aws/amazoncorretto/amazoncorretto:17
COPY target/moneywise.jar moneywise.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","moneywise.jar"]
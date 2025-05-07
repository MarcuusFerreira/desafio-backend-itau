FROM container-registry.oracle.com/graalvm/native-image:21 AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean install native:compile -Pnative

FROM alpine:3.21 AS runtime

WORKDIR /app

COPY --from=build /app/target/statistics /app

RUN apk add --no-cache libc6-compat &&  \
    chmod +x /app/statistics

CMD ["/app/statistics"]
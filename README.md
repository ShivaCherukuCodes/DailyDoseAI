# 📈 Stock Price Fetcher (Spring Boot + Cache)

This Spring Boot microservice fetches **real-time stock prices** from the [Twelve Data API](https://twelvedata.com/) and uses **Caffeine cache** with the **Cache-Aside Pattern** to minimize repeated calls and improve performance.

---

## 🚀 Features

- ✅ Real-time stock price fetch from external API
- ⚡ In-memory caching with Spring Cache + Caffeine
- 🧠 Cache-Aside Pattern for efficient API usage
- 🧪 Log tracking for cache hits & misses
- 💡 Clean exception handling and extensible structure

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Cache + [Caffeine](https://github.com/ben-manes/caffeine)
- Twelve Data API
- SLF4J Logger
- Lombok

---
🧠 Caching Behavior
On first request, it fetches from TwelveData API and logs the response.

Within 10 seconds, repeated requests for the same stock return from cache.

Logs show cache behavior (hit/miss) and timestamp.


🔗 API Endpoint
➤ GET /api/stocks/{symbol}
Returns the latest stock price for the given symbol.

✅ Example:
curl http://localhost:8080/api/stocks/AAPL

## 🔧 Configuration

### 📄 `application.yml`

```yaml
stock:
  api:
    key: YOUR_TWELVEDATA_API_KEY

spring:
  cache:
    cache-names: stocks
    caffeine:
      spec: maximumSize=1000,expireAfterWrite=10s

logging:
  level:
    org.springframework.cache.interceptor.CacheInterceptor: DEBUG


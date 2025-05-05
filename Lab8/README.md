# Лабораторна робота №5 - Інтеграція з API

## Опис проєкту
Цей проект імітує систему онлайн-покупок, де клієнти можуть переглядати продукти,
додавати товари в кошик для покупок, розміщувати замовлення та здійснювати платежі з інтеграцією зовнішнього API за допомогою Ktor клієнта.  Використовується сервіс jsonplaceholder.typicode.com для демонстрації різних типів HTTP запитів.

## Використаний сервіс
- **Сервіс**: JSONPlaceholder
- **Базова URL**: https://jsonplaceholder.typicode.com
- **Опис**: Безкоштовний фейковий REST API для тестування та прототипування

## Реалізовані ендпоінти

1. **GET /posts**
   - Опис: Отримання списку всіх постів
   - Headers: X-Custom-Header: TestValue
   - Відповідь: List<ApiPost>

2. **GET /posts/{id}**
   - Опис: Отримання конкретного поста за ID
   - Query Params: detailed=true
   - Відповідь: ApiPost

3. **POST /posts**
   - Опис: Створення нового поста
   - Content-Type: application/json
   - Тіло запиту: ApiPost(title, body, userId)
   - Відповідь: ApiPost

4. **PUT /posts/{id}**
   - Опис: Оновлення існуючого поста
   - Content-Type: application/json
   - Тіло запиту: ApiPost(id, title, body, userId)
   - Відповідь: ApiPost

5. **DELETE /posts/{id}**
   - Опис: Видалення поста за ID
   - Відповідь: HTTP статус

## Структура даних
```kotlin
data class ApiPost(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)

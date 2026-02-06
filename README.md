# Exercise 1: My Clean Code Principles

### My Reflection
1. **Choosing Good Names**: I prioritize descriptive naming to improve readability that supports clean code principles.
2. **Secure Coding Practices**: Previously, I didn't sanitize user inputs. I've learned this is a major vulnerability.
3. **Fixing Logic Issues**: Resolved the issue where the product form wouldn't create a product upon submission in Spring Boot.
4. **Security Improvements**: Sanitize all user inputs to prevent **SQL Injection** (such as using **Spring Data JPA**) and **XSS Scripting** (such as **Thymeleaf**).

---

# Exercise 2: Unit Testing & Code Coverage

### How I feel after writing unit tests
After writing unit tests, I feel more confident that the program is running properly. It provides a safety net for future changes.

### Key Takeaways on Code Coverage
* **What is it?** A metric that shows how much of the source code is executed during tests.
* **The 100% Myth**: 100% code coverage does **not** mean the code is bug-free or that the logic is correct.
* **The Goal**: It only guarantees that every line was executed. To truly verify a program, I must implement diverse test scenarios and robust testing practices.

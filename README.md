# Exercise 1: My Clean Code Principles

### My Reflection
1. **What have I implemented the principles of clean code?**: I prioritize descriptive naming to improve readability that supports clean code principles.
2. **Secure Coding Practices**: I didn't sanitize user inputs at all but I know that is a major vulnerability.
3. **How to Fix Logic Issues?**: By resolving the issue where the product form wouldn't create a product upon submission in Spring Boot.
4. **Security Improvements**: Sanitize all user inputs to prevent **SQL Injection** (such as using **Spring Data JPA**) and **XSS Scripting** (such as **Thymeleaf**).

---

# Exercise 2: Unit Testing & Code Coverage

### How I feel after writing unit tests
After writing unit tests, I feel more confident that the program is running properly. It provides a safety net for future changes.

### Key Takeaways on Code Coverage
* **What is it?** A metric that shows how much of the source code is executed during tests.
* **The 100% Myth**: 100% code coverage does **not** mean the code is bug-free or that the logic is correct.
* **The Goal**: It only guarantees that every line was executed. To truly verify a program, I must implement diverse test scenarios comprehensively and robust testing practices.

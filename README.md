# Module 01

## Exercise 1: My Clean Code Principles

### My Reflection
1. **What have I implemented the principles of clean code?**: I prioritize descriptive naming to improve readability that supports clean code principles.
2. **Secure Coding Practices**: I didn't sanitize user inputs at all but I know that is a major vulnerability.
3. **How to Fix Logic Issues?**: By resolving the issue where the product form wouldn't create a product upon submission in Spring Boot.
4. **Security Improvements**: Sanitize all user inputs to prevent **SQL Injection** (such as using **Spring Data JPA**) and **XSS Scripting** (such as **Thymeleaf**).

---

## Exercise 2: Unit Testing & Code Coverage

### How I feel after writing unit tests
After writing unit tests, I feel more confident that the program is running properly. It provides a safety net for future changes.

### Key Takeaways on Code Coverage
* **What is it?** A metric that shows how much of the source code is executed during tests.
* **The 100% Myth**: 100% code coverage does **not** mean the code is bug-free or that the logic is correct.
* **The Goal**: It only guarantees that every line was executed. To truly verify a program, I must implement diverse test scenarios comprehensively and robust testing practices.

# Module 02

## Reflection

### 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
- SAST security issues: By adding Scorecard workflow for supply-chain security and adding PMD code scanning workflow
- CI-Tests security issues: By adding PMD code scanning workflow
- Token-Permissions security issues: By restricting permissions for GITHUB_TOKEN by doing .github/workflows/sudo.yml, .github/workflows/ci.yml, .github/workflows/node-workflow-1.yml, 	.github/workflows/super-linter.yml, .github/workflows/workflow.yml, .github/workflows/codeql.yml

### 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
No, the implementation only partially meets the definition, with significant gaps in both CI and CD. The reasons are limited continuous integration. This implementation has only basic testing, The primary CI workflow (`ci.yml`) runs unit tests on push and pull requests, which is a fundamental CI practice. However, the implementation is lacks comprehensive CI practices such as code coverage reporting, static code analysis beyond security scanning (like PMD for code quality), or automated integration testing. While additional workflows exist (CodeQL, PMD, super-linter), they are not integrated into the core CI pipeline as required checks, meaning failures in linting or code quality analysis don't block merges, defeating the purpose of continuous integration. Besides that, this repository has zero CD implementation. There are no workflows that automatically build, package, or deploy the application to any environment (staging, production, etc.). A true CD pipeline would automatically deploy successful builds to target environments. The current setup only includes code quality and security checks but provides no mechanism for releasing or deploying the application, which is essential for continuous deployment.
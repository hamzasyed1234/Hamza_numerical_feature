# Numerical Report Feature

## Project Structure

```
numerical_feature/
├── backend/          ← Java Rules Engine (Maven project)
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/marketing/
│       │   ├── NumericalEngine.java   ← Core rules engine
│       │   ├── ReportSummary.java     ← Stats data class
│       │   └── Main.java              ← CLI entry point
│       └── test/java/com/marketing/
│           └── NumericalEngineTest.java ← JUnit 5 tests
└── frontend/
    └── index.html    ← Self-contained HTML/CSS/JS dashboard
```

---

## Backend — Java Rules Engine

### Prerequisites
- Java 11 or higher
- Apache Maven 3.6+

### Compile & Run Tests

```bash
cd backend

# Run all automated tests
mvn test

# Build the fat JAR
mvn package -DskipTests

# Run the CLI (optional — pass any number 1-100)
java -jar target/numerical-engine-1.0.0.jar 20
```

### Rules
| Condition                    | Output         |
|------------------------------|----------------|
| Multiple of 3                | Fizz           |
| Multiple of 5                | Buzz           |
| Multiple of 7                | Whizz          |
| Multiple of 3 **and** 5      | FizzBuzz       |
| Multiple of 3 **and** 7      | FizzWhizz      |
| Multiple of 5 **and** 7      | BuzzWhizz      |
| Multiple of 3, 5 **and** 7   | FizzBuzzWhizz  |
| None of the above            | The number itself |

### Validation
- Input ≤ 0 → throws `IllegalArgumentException` with clear message
- Input > 100 → throws `IllegalArgumentException`

---

## Frontend — HTML Dashboard

### How to Open

No build step or server required.

1. Navigate to the `frontend/` folder.
2. Open `index.html` in any modern web browser (Chrome, Firefox, Edge, Safari).

### Features
- Enter a number (1–100) and click **Generate Report** (or press Enter).
- The dashboard displays:
  - Total items processed
  - Count of **Fizz** occurrences (including FizzBuzz, FizzWhizz, FizzBuzzWhizz)
  - Count of **Buzz** occurrences
  - Count of **Whizz** occurrences
- Colour-coded token sequence for quick visual inspection.
- Client-side validation mirrors the backend security rules.

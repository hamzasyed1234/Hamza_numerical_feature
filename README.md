Hamza_numerical_feature/

├── backend/

│   ├── pom.xml

│   ├── src/

│   │   ├── Main.java            ← Entry point (CLI mode or --server mode)

│   │   ├── NumericalEngine.java ← Core rules engine + report logic

│   │   └── Server.java          ← HTTP server exposing the engine as an API

│   └── test/

│       └── NumericalEngineTest.java

└── frontend/

└── index.html               ← Dashboard that calls the Java backend

The frontend doesn't reimplement the rules — it calls the Java backend over HTTP
and renders whatever it returns. **The backend must be running before the
frontend can generate a report.**

---

## Backend (Java)

**Prerequisites:** Java 11+, Maven 3.6+

```bash
cd backend
mvn test       # run automated tests
mvn package    # build target/numerical-engine-1.0.0.jar
```

**Run as the API server** (needed for the frontend):
```bash
java -jar target/numerical-engine-1.0.0.jar --server
```
Outputs `Server running at http://localhost:8080/api/report?limit=20`. Custom port: add a number after `--server`.

**Or run as a plain CLI** (prints straight to console, no server):
```bash
java -jar target/numerical-engine-1.0.0.jar 20
```

### API

`GET /api/report?limit=N`

```json
{ "total": 20, "fizzCount": 6, "buzzCount": 4, "whizzCount": 2, "sequence": ["1","2","Fizz", "..."] }
```
Errors (limit ≤ 0 or > 100) return `400` with `{ "error": "..." }`.

### Rules

| Condition          | Output    |
|---------------------|-----------|
| Multiple of 3       | Fizz      |
| Multiple of 5       | Buzz      |
| Multiple of 7       | Whizz     |
| 3 and 5             | FizzBuzz  |
| 3 and 7             | FizzWhizz |
| 5 and 7             | BuzzWhizz |
| None                | The number itself |

---

## Frontend

1. Start the backend server first (see above).
2. Open `frontend/index.html` directly in any browser — no build step.

If you see "Cannot reach the backend," the Java server isn't running on port 8080.

# Customer Rewards Service

This service is responsible for calculating the rewards points for customers based on their transactions.

## Endpoints

- `GET /custRewards/monthly/{customerId}`: Get the monthly rewards points for a specific customer. Requires `month` and `year` as request parameters.
- `GET /custRewards/total/{customerId}`: Get the total rewards points for a specific customer.

## Setup

1. Clone the repository
2. Install the dependencies using `mvn install`
3. Run the application using `mvn spring-boot:run`

## Running Tests

Run the tests using `mvn test`


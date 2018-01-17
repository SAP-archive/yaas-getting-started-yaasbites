---
title: Testing
---

### Performance testing
Check and record performance metrics for every REST service deployed to a production environment to create a baseline for all REST services, and ensure the expected service responsiveness.

### Integration testing
Every API Resource should provide a DELETE method for the collection endpoint to easily wipe out test data and simplify integration testing. For example: `DELETE /{tenant}/products`. This endpoint should be authorized with a special administrative scope.

# Event Management Platform API Documentation

## Overview
This documentation covers the RESTful APIs powering our comprehensive Event Management Platform. The platform facilitates seamless interaction between event organizers (Exhibitors and Partners) and attendees (Visitors and Speakers), supporting various event types including exhibitions and seminars.

## System Architecture
- Backend: Spring Boot with Hibernate ORM
- Database: PostgreSQL
- Caching Layer: Redis
- Deployment: Google Cloud Platform (GCP)

## Core Features
1. Event Management
   - Event creation and modification
   - Exhibition space management
   - Seminar scheduling
   - Speaker management

2. Ticket System
   - Ticket generation
   - Booking management
   - Entry validation
   - Capacity management

3. User Management
   - Role-based access control
   - Profile management
   - Authentication and authorization
   - Session handling

## Authentication
All API endpoints are secured using JWT (JSON Web Tokens). Include the bearer token in the Authorization header for authenticated requests.

## Performance Optimization
- Redis caching implementation for frequently accessed data
- Optimized database queries
- Efficient session management

## API Versioning
Current Version: v1
Base URL: `https://kuivent-event-management-y2muiw3wba-et.a.run.app/api/v1`

## Documentation Link
For detailed API documentation and examples, visit our Postman Documentation: https://documenter.getpostman.com/view/25526934/2sAYBUDs1Y 

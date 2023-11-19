# Fleet Database SQL Script

The `create_schema.sql` script defines the database schema for a fleet management system, including drones, loads, medications, battery history, and their relationships. The script was generated using MySQL Workbench from this database diagram.

![Database Diagram](diagram.png)

## Purpose

The purpose of this script is to provide the necessary database structure for a fleet management system that involves drones capable of carrying medications. The database design encompasses various entities such as drones, loads, medications, and battery history, allowing for efficient tracking and management of the fleet.

## Database Schema Overview

The database schema includes the following tables:

- **Load**: Represents a load of medications with information such as weight, status, and associated drone.
- **Drone**: Describes the characteristics of drones, including serial number, battery capacity, and status.
- **Medication**: Contains information about medications, including name, code, weight, and an optional image URL.
- **Battery_History**: Records historical battery data for drones, including recorded timestamp and remaining capacity.
- **Load_has_Medication**: Facilitates a many-to-many relationship between loads and medications, allowing for efficient management of loaded medications.
- **Drone_Model**: A lookup table that contains drone models and their corresponding weight limits.



## Instructions

- Execute `create_schema.sql` script to create the fleet management database.
- You can execute `insert_data.sql` to create some dummy data on **Drone** and **Medication** tables


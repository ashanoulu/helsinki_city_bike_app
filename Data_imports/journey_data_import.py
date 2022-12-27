import csv
from pandas import *
import mysql.connector

db_connection = mysql.connector.connect(
  host="localhost",
  user="root",
  password="",
  database="helsinki_bike"
)

def save_data():

    # CHANGE THE FILE PATH TO THE DATA SET NEED TO BE IMPORT
    data = read_csv("data/2021-07.csv", sep=",")

    data_to_insert = []
    for i in data.index:
        if data['Covered distance (m)'][i] > 10 and data['Duration (sec.)'][i] > 10:
            data_record = (data['Departure'][i], data['Return'][i], int(data['Departure station id'][i]),
                           data['Departure station name'][i], int(data['Return station id'][i]),
                           data['Return station name'][i], int(data['Covered distance (m)'][i]),
                           int(data['Duration (sec.)'][i]))
            data_to_insert.append(data_record)

    print("All data filtered")
    db_cursor = db_connection.cursor()
    sql = "INSERT INTO journeys (departure_time, return_time, departure_station_id, departure_station_name, " \
          "return_station_id, return_station_name, covered_destination, duration) " \
          "VALUES (%s, %s, %s, %s, %s, %s, %s, %s)"

    db_cursor.executemany(sql, data_to_insert)

    db_connection.commit()

    print(db_cursor.rowcount, "was inserted.")



def main():
    save_data()


if __name__ == "__main__":
    main()

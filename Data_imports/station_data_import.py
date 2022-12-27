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
    data = read_csv("data/bike_stations.csv", sep=",")

    data_to_insert = []
    for i in data.index:
        data_record = (str(data['ID'][i]), data['Nimi'][i], data['Namn'][i], data['Name'][i], data['Osoite'][i],
                       data['Address'][i], data['City'][i], data['Stad'][i], data['Operator'][i],
                       int(data['Capacity'][i]), str(data['x'][i]), str(data['y'][i]))
        data_to_insert.append(data_record)

    print("Converted to a list of tuples")
    db_cursor = db_connection.cursor()
    sql = "INSERT INTO station (station_id, nimi, namn, name, osoite, address, city, stad, operator, capacity, " \
          "coordinate_x, coordinate_y) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"

    db_cursor.executemany(sql, data_to_insert)

    db_connection.commit()

    print(db_cursor.rowcount, "was inserted.")


def main():
    save_data()


if __name__ == "__main__":
    main()

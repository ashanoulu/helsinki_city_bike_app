<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
          <form class=form-data>
            <h3>Station Data - {{station.name}}</h3>
              <div class="row">
                <label>Nimi</label><br>
                <input v-model="station.nimi" type="text"><br>

                <label>Namn</label><br>
                <input v-model="station.namn" type="text"><br>

                <label>Name</label><br>
                <input v-model="station.name" type="text"><br>

                <label>Osoite</label><br>
                <input v-model="station.osoite" type="text"><br>

                <label>Address</label><br>
                <input v-model="station.address" type="text"><br>

                <label>City</label><br>
                <input v-model="station.city" type="text"><br>

                <label>Stad</label><br>
                <input v-model="station.stad" type="text"><br>

                <label>Operator</label><br>
                <input v-model="station.operator" type="text"><br>

                <label>Capacity</label><br>
                <input v-model="station.capacity" type="number"><br>

                <label>X coordinate</label><br>
                <input v-model="station.coordinateX" type="text"><br>

                <label>Y coordinate</label><br>
                <input v-model="station.coordinateY" type="text"><br>
              </div>
            <button @click.prevent="addNewStation" v-if="form_type == 'New'">Add New Station</button>
            <button @click.prevent="updateStation(station.stationId)" v-if="form_type == 'Edit'">Save Changes</button>
          </form>
          <div v-if="form_type == 'View'">
            <GMap v-if="station.coordinateY"
                :coord-x="station.coordinateX"
                :coord-y="station.coordinateY"
            />
          </div>
          <div class="chart" v-if="form_type == 'View'">
            <Charts v-if="station_stat.number_of_ret_dep_distance_data.labels"
                :chart-data="station_stat.number_of_ret_dep_distance_data"
            />
            <Charts v-if="station_stat.average_ret_dep_distance_data.labels"
                :chart-data="station_stat.average_ret_dep_distance_data"
            />
            <Charts v-if="station_stat.average_ret_dep_duration_data.labels"
                :chart-data="station_stat.average_ret_dep_duration_data"
            />
            <Charts v-if="station_stat.top_departure_stations.labels"
                :chart-data="station_stat.top_departure_stations"
            />
            <Charts v-if="station_stat.top_return_stations.labels"
                :chart-data="station_stat.top_return_stations"
            />
          </div>
        </div>
        <div class="col-1"></div>
      </div>
    </div>
  </div>
</template>

<script>
import GMap from "../Gmap"
import Charts from "../Chart"
import { useRoute } from 'vue-router';
import axios from "axios";
import router from "@/route";
import { useLoading } from 'vue3-loading-overlay';
import 'vue3-loading-overlay/dist/vue3-loading-overlay.css';

export default {
  name: "station-view",
  components: {
    Charts,
    GMap
  },
  data() {
    return {
      form_type: '',
      station_id: '',
      station: {
        id: '',
        stationId: '',
        nimi: '',
        namn: '',
        name: '',
        osoite: '',
        address: '',
        city: '',
        stad: '',
        operator: '',
        capacity: 0,
        coordinateX: '',
        coordinateY: '',
      },
      station_stat: {
        number_of_ret_dep_distance_data: {},
        average_ret_dep_distance_data: {},
        average_ret_dep_duration_data: {},
        top_departure_stations: {},
        top_return_stations: {}
      }
    }
  },
  mounted() {
    const route = useRoute();
    this.form_type = route.query.type;
    this.station_id = route.query.station_id;

    if (this.form_type != 'New'){
      this.getStationData()
    }
  },
  methods:{
    getStationData() {
      if (this.station_id) {
        let loader = useLoading()
        loader.show()
        axios.get("/api/v1/station/" + this.station_id).then(response =>{
          loader.hide()
          if (response.data) {
            this.station = response.data.station
            let stat = response.data.stationStatistics
            this.station_stat.number_of_ret_dep_distance_data = {
              labels: ['Total Departures', 'Total Arrivals(Returns)'],
              datasets: [
                {
                  label: 'Total Departures and Returns',
                  backgroundColor: '#2979ff',
                  data: [stat.departureCount, stat.returnCount]
                }
              ]
            }
            this.station_stat.average_ret_dep_distance_data = {
              labels: ['Average Distance of Departures', 'Average Distance of Arrivals(Returns)'],
              datasets: [
                {
                  label: 'Average Distance of Departures and Returns in KM',
                  backgroundColor: '#2979ff',
                  data: [(stat.averageDepartureDistance) / 1000, (stat.averageReturnDistance) / 1000]
                }
              ]
            }
            this.station_stat.average_ret_dep_duration_data = {
              labels: ['Average Duration of Departures', 'Average Duration of Arrivals(Returns)'],
              datasets: [
                {
                  label: 'Average Duration of Departures and Returns in Minutes',
                  backgroundColor: '#2979ff',
                  data: [Math.floor((stat.averageDepartureDuration) / 60), Math.floor((stat.averageReturnDuration) / 60)]
                }
              ]
            }
            let dep_label = []
            let dep_data = []
            stat.departStationStat.forEach(data => {
              dep_data.push(data.total)
              dep_label.push(data.stationName)
            })

            this.station_stat.top_departure_stations = {
              labels: dep_label,
              datasets: [
                {
                  label: 'Top 5 departure stations',
                  backgroundColor: '#2979ff',
                  data: dep_data
                }
              ]
            }

            let ret_label = []
            let ret_data = []
            stat.returnStationStat.forEach(data => {
              ret_data.push(data.total)
              ret_label.push(data.stationName)
            })

            this.station_stat.top_return_stations = {
              labels: dep_label,
              datasets: [
                {
                  label: 'Top 5 return stations',
                  backgroundColor: '#2979ff',
                  data: ret_data
                }
              ]
            }
          }
        })
      }
    },
    addNewStation() {
      axios.post("/api/v1/station", this.station).then(response => {
        if (response.data) {
          this.$swal('Success!', 'You have successfully add new station!', 'success');
          this.station.stationId = response.data.stationId
          this.station_id = response.data.stationId
          this.form_type = 'Edit'
          router.push({ path: "/station", query: { station_id: response.data.stationId, type: 'Edit' } })
        }
      }).catch(e => {
        alert(e)
      })
    },

    updateStation(station_id) {
      axios.put("/api/v1/station/" + station_id, this.station).then(response => {
        if (response.data) {
          this.$swal('Success!', 'You have successfully updated station data!', 'success');
        }
      }).catch(e => {
        alert(e)
      })
    }
  }
}
</script>

<style scoped>
.form-data {
  margin-bottom: 2em;
  margin-top: 2em;
  margin-right: 10em;
  margin-left: 10em;
}
.chart {
  margin-bottom: 10em;
}
</style>

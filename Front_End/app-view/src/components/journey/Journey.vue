<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
          <form class="search-form">
            <input v-model="departure_station" class="input" type="text" placeholder="Departure Station">
            <input v-model="return_station" class="input" type="text" placeholder="Return Station">
            <button @click.prevent="getJourneys" type="button" class="btn btn-primary">Filter</button>
          </form>
        </div>
        <div class="col-3"></div>
      </div>
      <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
          <div class="dropdown">
            <select @change="getJourneys" v-model="size" class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <option :value="10">10</option>
              <option :value="50">50</option>
              <option :value="100">100</option>
            </select>
          </div>
          <table id="tableComponent" class="table table-bordered table-striped">
            <thead>
            <tr>
              <th>Departure Station<i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th>Return Station<i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th>Covered Distance (KM)<i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th>Duration (MIN)<i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(data, index) in journey_list" :key="index">
              <td>{{data.departureStationName}}</td>
              <td>{{data.returnStationName}}</td>
              <td>{{(data.coveredDestination)/1000}}</td>
              <td>{{Math.floor((data.duration)/60)}}</td>
            </tr>
            </tbody>
          </table>
          <div>
            <paginate
                v-model="page"
                :page-count="total_pages"
                :click-handler="getJourneys"
                :prev-text="'Prev'"
                :next-text="'Next'"
                :container-class="'paginate'"
            >
            </paginate>
          </div>
        </div>
        <div class="col-3"></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import paginate from "vuejs-paginate-next";
import { useLoading } from 'vue3-loading-overlay';
import 'vue3-loading-overlay/dist/vue3-loading-overlay.css';
export default {
  name: "journey-view",
  components: {
    paginate
  },
  data() {
    return {
      size: 10,
      page: 0,
      direction: 'ASC',
      departure_station: '',
      return_station: '',
      journey_list: [],
      total_pages: 0
    }
  },
  mounted() {
    this.getJourneys()
  },
  methods: {
    getJourneys() {
      let loader = useLoading();
      loader.show();
      axios.get("/api/v1/journeys", {
        headers: {
          departureStation: this.departure_station,
          returnStation: this.return_station,
          direction: this.direction,
          page: this.page,
          size: this.size
        }
      }).then(response => {
        loader.hide()
        if (response.data) {
          this.journey_list = response.data.content
          this.page = response.data.number
          this.size = response.data.size
          this.total_pages = response.data.totalPages
          console.log(this.data)
        }

      }).catch(e => {
        alert(e)
        loader.hide()
      })
    }
  }
}
</script>

<style scoped>
@import "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css";
.row {
  padding: 10px;
}
.search-form {
  padding-left: 10px;
}
.input {
  margin: 10px;
}
.paginate {
  display: -webkit-inline-box;
  margin: 20px;
}
</style>

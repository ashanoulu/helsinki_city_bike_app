<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-4"></div>
        <div class="col-4">
          <form class="search-form">
            <input v-model="name" class="input" placeholder="Station Name" type="text">
            <button class="btn btn-primary" type="button" @click.prevent="getStations">Filter</button>
            <button class="btn btn-primary new-station" type="button" @click.prevent="addNewStation">Add new Station</button>
          </form>
        </div>
        <div class="col-4"></div>
      </div>
      <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
          <div class="dropdown">
            <select id="dropdownMenuButton" v-model="size" aria-expanded="false" aria-haspopup="true"
                    class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" type="button" @change="getStations">
              <option :value="10">10</option>
              <option :value="50">50</option>
              <option :value="100">100</option>
            </select>
          </div>
          <table id="tableComponent" class="table table-bordered table-striped tablemobile">
            <thead>
            <tr>
              <th>Station ID<i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th>Nimi<i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th>Namn<i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th>Name<i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th>Address<i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th>City<i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th>Capacity<i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th><i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th><i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
              <th><i aria-label='Sort Icon' class="bi bi-sort-alpha-down"></i></th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(data, index) in station_list" :key="index">
              <td>{{ data.stationId }}</td>
              <td>{{ data.nimi }}</td>
              <td>{{ data.namn }}</td>
              <td>{{ data.name }}</td>
              <td>{{ data.address }}</td>
              <td>{{ data.city }}</td>
              <td>{{ data.capacity }}</td>
              <td>
                <button @click.prevent="showStation(data.stationId)" class="btn btn-primary" type="button">View</button>
              </td>
              <td>
                <button @click.prevent="editStation(data.stationId)" class="btn btn-warning" type="button">Edit</button>
              </td>
              <td>
                <button @click.prevent="deleteStation(data.stationId)" class="btn btn-danger" type="button">Delete</button>
              </td>
            </tr>
            </tbody>
          </table>
          <div>
            <paginate
                v-model="page"
                :click-handler="getStations"
                :container-class="'paginate'"
                :next-text="'Next'"
                :page-count="total_pages"
                :prev-text="'Prev'"
            >
            </paginate>
          </div>
        </div>
        <div class="col-1"></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import paginate from "vuejs-paginate-next";
import router from "@/route";
import { useLoading } from 'vue3-loading-overlay';
import 'vue3-loading-overlay/dist/vue3-loading-overlay.css';

export default {
  name: "stations-view",
  components: {
    paginate
  },
  data() {
    return {
      size: 10,
      page: 0,
      direction: 'ASC',
      name: '',
      station_list: [],
      total_pages: 0
    }
  },
  mounted() {
    this.getStations()
  },
  methods: {
    getStations() {
      let loader = useLoading()
      loader.show()
      axios.get("/api/v1/stations", {
        headers: {
          name: this.name,
          direction: this.direction,
          page: this.page,
          size: this.size
        }
      }).then(response => {
        loader.hide()
        if (response.data) {
          this.station_list = response.data.content
          this.page = response.data.number
          this.size = response.data.size
          this.total_pages = response.data.totalPages
          console.log(response.data.content)
        }
      }).catch(e => {
        alert(e)
      })
    },
    addNewStation() {
      router.push({ path: "/station", query: { type: 'New'} })
    },
    showStation(station_id) {
      router.push({ path: "/station", query: { station_id: station_id, type: 'View' } })
    },
    editStation(station_id) {
      router.push({ path: "/station", query: { station_id: station_id, type: 'Edit' } })
    },
    deleteStation(station_id) {
      if (station_id) {
        axios.delete("/api/v1/station/" + station_id).then(response => {
          if (response) {
            this.$swal('Success!', 'You have successfully deleted the station!', 'success')
          }
        }).catch(e => {
          alert(e)
        })
      }
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

@media only screen and (max-width: 480px) {
  /* horizontal scrollbar for tables if mobile screen */
  .tablemobile {
    overflow-x: auto;
    display: block;
  }
  .new-station {
    margin-left: 5em;
  }
}
</style>

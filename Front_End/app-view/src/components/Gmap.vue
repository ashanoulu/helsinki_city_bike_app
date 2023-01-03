<template>
  <GoogleMap
      api-key="AIzaSyCsy7eXyms2hsitrvkpDialE6VNkRw8bqc"
      style="width: 100%; height: 500px"
      :center="center"
      :zoom="15"
  >
    <Marker :options="{ position: center }"

    />
  </GoogleMap>
</template>

<script>
import { defineComponent } from "vue";
import { GoogleMap, Marker } from "vue3-google-map";

export default defineComponent({
  name: "g-map",
  components: { GoogleMap, Marker },
  props: [
      "coordX",
      "coordY"
  ],
  setup(props) {
    const center = { lat: Number(props.coordY), lng: Number(props.coordX) };

    return { center };
  },
  methods: {

  }
});
</script>

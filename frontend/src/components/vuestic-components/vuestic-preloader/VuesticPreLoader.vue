<template>
  <div id="app">
     <orbit-spinner
          :animation-duration="1000"
          :size="130"
          :color="'#333759'"
     />
  </div>
</template>

<script>
  // To use minified css and js files instead of .vue single file components:
  // import 'epic-spinners/dist/lib/epic-spinners.min.css'
  // import {AtomSpinner} from 'epic-spinners/dist/lib/epic-spinners.min.js'
  
  import {OrbitSpinner} from 'epic-spinners'
  export default {
    name: 'vuestic-pre-loader',
    components: {
      OrbitSpinner
    }
  }
</script>

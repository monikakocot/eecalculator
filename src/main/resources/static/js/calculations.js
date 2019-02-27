var percent = 0.55;

var id = '';

function tN(num) {
  return num.replace ? +num.replace(',', '.') : +num;
}

function map(arr, f) {
  var array = [];
  for (var i = 0; i < arr.length; i++) {
    array.push(f(arr[i], i));
  }
  return array;
}

function checkNumbers(arr) {
  for (var i = 0; i < arr.length; i++) {
    var n = arr[i];
    if (n === '' || Number.isNaN(tN(n))) return false;
  }
  return true;
}

var app = new Vue({
  el: '#app',
  data: {
    time: 168,
    compressors: [
      {
        name: '',
        power: '',
        load: '0.55',
      },
    ],
  },
  computed: {
    potencialEnergyTotal: function() {
      var total = 0;
      for (var i = 0; i < this.results.length; i++)
        total += tN(this.results[i].potencialEnergyGJ);
      return total;
    },

//    totalRecoveryShare: function() {
//      var that = this;
//      return map(this.results, function(obj, i) {
//        return obj.potencialEnergyGJ !== ''
//          ? (obj.potencialEnergyGJ / that.potencialEnergyTotal * 100).toFixed(
//              0,
//            ) + '%'
//          : '';
//      });
//    },

//    moneyToSave: function() {
//      return (this.potencialEnergyTotal / 41.868 * 150).toFixed(0) * 10;
//    },

    results: function() {
      var that = this;
      return map(this.$data.compressors, function(c, i) {
        var obj = {};

        obj.energy = (function() {
          var time = that.$data.time;
          if (checkNumbers([c.power, c.load, time]))
            return (
              tN(c.power) *
              tN(c.load) *
              tN(time) *
              (4 + 2 / 7) *
              12
            ).toFixed(0);
          else return '';
        })();

        obj.potencialEnergy = (function() {
          var eC = tN(obj.energy);
          return obj.energy !== ''
            ? (eC * percent).toFixed(0)
            : '';
        })();

        obj.potencialEnergyGJ =
          obj.potencialEnergy !== ''
            ? (obj.potencialEnergy / 1000 * 3.6).toFixed(0)
            : '';

        obj.potencialPower = checkNumbers([c.load, c.power])
          ? (tN(c.power) * tN(c.load) * percent).toFixed(0)
          : '';

        return obj;
      });
    },
  },
  methods: {
    add: function() {
      this.$data.compressors.push({name: '', power: '', load: '0.55'});
    },

    remove: function(index) {
      this.$data.compressors.splice(index, 1);
    },
  },
});
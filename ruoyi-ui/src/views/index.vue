<template>
  <div class="app-container"  >

      <el-col  style="padding-left: 20px">
        <h1>智慧充电管理平台</h1>



      </el-col>
      <el-col>
        <template>
          <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
            <el-table-column label="模块" prop="modle" width="180">
            </el-table-column>
            <el-table-column label="负责人" prop="name" width="180">
            </el-table-column>

          </el-table>
        </template>
      </el-col>

  

  </div>
</template>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>
<script>
export default {
  name: "Index",
  data() {
    return {
      //表格数据
      tableData: [{
        name: '林鸿港',
        modle: '充电站管理'
      }, {
        name: ' ',
        modle: '故障管理'
      }, {
        name: '丰刘星',
        modle: '充电桩管理'
      }, {
        name: ' ',
        modle: '充电枪管理'
      }, {
        name: '白舜天',
        modle: '运营商管理'
      }, {
        name: '',
        modle: '计费管理'
      }, {
        name: '田永祥',
        modle: '用户管理'
      }, {
        name: ' ',
        modle: '优惠管理'
      }, {
        name: '黄泽波',
        modle: '订单管理'
      }, {
        name: ' ',
        modle: '报表管理'
      }],


      // 版本号
      version: "3.8.5"
    };
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      var t = Math.floor(rowIndex / 2);
      var tt = t % 2
      if (tt == 0) {
        return 'warning-row';
      } else {
        return 'success-row';
      }
      return "";
    },
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  background-image: url("../assets/images/index-background.jpg");

  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans",
  "Helvetica Neue",
  Helvetica,
  Arial,
  sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>


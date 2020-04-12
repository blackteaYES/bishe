<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="设备位置" prop="mePosition">
        <el-input
          v-model="queryParams.mePosition"
          placeholder="请输入设备位置"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备状态 " prop="meState">
        <!-- <el-input
          v-model="queryParams.meState"
          placeholder="请输入设备状态 是否损坏 0完好 1损坏 "
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select v-model="queryParams.meState" filterable placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="预约人数" prop="maSize">
        <el-input
          v-model="queryParams.maSize"
          placeholder="请输入预约人数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图片名称" prop="meImgName">
        <el-input
          v-model="queryParams.meImgName"
          placeholder="请输入图片名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="购入时间" prop="buyDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.buyDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择购入时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="购入人" prop="buyName">
        <el-input
          v-model="queryParams.buyName"
          placeholder="请输入购入人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="维修次数" prop="pepairSize">
        <el-input
          v-model="queryParams.pepairSize"
          placeholder="请输入维修次数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['basicfunction:equipment:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['basicfunction:equipment:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['basicfunction:equipment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basicfunction:equipment:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="equipmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备ID" align="center" prop="meId" />
      <el-table-column label="设备位置" align="center" prop="mePosition" />
      <el-table-column label="设备状态 是否损坏 0完好 1损坏 " align="center" prop="meState" >
        <template  slot-scope="scope">
            <span v-if="scope.row.meState== 0">
                完好
            </span>
             <span v-if="scope.row.meState== 1">
               损坏
            </span>
        </template>
      </el-table-column>
      <el-table-column label="预约人数" align="center" prop="maSize" />
      <el-table-column label="图片名称" align="center" prop="meImgName" />
      <el-table-column label="购入时间" align="center" prop="buyDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.buyDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="购入人" align="center" prop="buyName" />
      <el-table-column label="维修次数" align="center" prop="pepairSize" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basicfunction:equipment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basicfunction:equipment:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改设备对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备位置" prop="mePosition">
          <el-input v-model="form.mePosition" placeholder="请输入设备位置" />
        </el-form-item>
        <el-form-item label="设备状态 是否损坏 0完好 1损坏 " prop="meState">
          <el-input v-model="form.meState" placeholder="请输入设备状态 是否损坏 0完好 1损坏 " />
        </el-form-item>
        <el-form-item label="预约人数" prop="maSize">
          <el-input v-model="form.maSize" placeholder="请输入预约人数" />
        </el-form-item>
        <el-form-item label="图片名称" prop="meImgName">
          <el-input v-model="form.meImgName" placeholder="请输入图片名称" />
        </el-form-item>
        <el-form-item label="购入时间" prop="buyDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.buyDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择购入时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="购入人" prop="buyName">
          <el-input v-model="form.buyName" placeholder="请输入购入人" />
        </el-form-item>
        <el-form-item label="维修次数" prop="pepairSize">
          <el-input v-model="form.pepairSize" placeholder="请输入维修次数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listEquipment, getEquipment, delEquipment, addEquipment, updateEquipment, exportEquipment } from "@/api/basicfunction/equipment";

export default {
  name: "Equipment",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 设备表格数据
      equipmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mePosition: undefined,
        meState: undefined,
        maSize: undefined,
        meImgName: undefined,
        buyDate: undefined,
        buyName: undefined,
        pepairSize: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      options: [{
        value: '0',
        label: '完好'
      }, {
        value: '1',
        label: '损坏'
      }]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备列表 */
    getList() {
      this.loading = true;
      listEquipment(this.queryParams).then(response => {
        this.equipmentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        meId: undefined,
        mePosition: undefined,
        meState: undefined,
        maSize: undefined,
        meImgName: undefined,
        buyDate: undefined,
        buyName: undefined,
        pepairSize: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.meId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const meId = row.meId || this.ids
      getEquipment(meId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.meId != undefined) {
            updateEquipment(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addEquipment(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const meIds = row.meId || this.ids;
      this.$confirm('是否确认删除设备编号为"' + meIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delEquipment(meIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有设备数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportEquipment(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
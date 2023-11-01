<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu mode="inline" :style="{ height: '100%', borderRight: 0 }">
        <a-sub-menu key="sub1">
          <template #title>
            <span>
              <user-outlined />
              subnav 11111
            </span>
          </template>
          <a-menu-item key="1">option1</a-menu-item>
          <a-menu-item key="2">option2</a-menu-item>
          <a-menu-item key="3">option3</a-menu-item>
          <a-menu-item key="4">option4</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <template #title>
            <span>
              <laptop-outlined />
              subnav 2
            </span>
          </template>
          <a-menu-item key="5">option5</a-menu-item>
          <a-menu-item key="6">option6</a-menu-item>
          <a-menu-item key="7">option7</a-menu-item>
          <a-menu-item key="8">option8</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub3">
          <template #title>
            <span>
              <notification-outlined />
              subnav 3
            </span>
          </template>
          <a-menu-item key="9">option9</a-menu-item>
          <a-menu-item key="10">option10</a-menu-item>
          <a-menu-item key="11">option11</a-menu-item>
          <a-menu-item key="12">option12</a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
      <a-list item-layout="vertical" size="large" :pagination="pagination" :data-source="ebookslist"
        :grid="{ gutter: 16, column: 4 }">
        <template #renderItem="{ item }">
          <a-list-item key="item.name">
            <template #actions>
              <span v-for="{ icon, text } in actions" :key="icon">
                <component :is="icon" style="margin-right: 8px" />
                {{ text }}
              </span>
            </template>
            <a-list-item-meta :description="item.description">
              <template #title>
                <a :href="item.href">{{ item.name }}</a>
              </template>
              <template #avatar><a-avatar :src="item.cover" /></template>
            </a-list-item-meta>
            {{ item.content }}
          </a-list-item>
        </template>
      </a-list>
    </a-layout-content>

  </a-layout>
</template>

<script lang="ts">
import { computed, defineComponent, onMounted, ref } from 'vue';// @ is an alias to /src
import axios from 'axios';

export default defineComponent({
  name: 'HomeView',
  setup() {

    const pagination = {
      onChange: (page: number) => {
        console.log(page);
      },
      pageSize: 8,
    };

    const ebookslist = ref();
    onMounted(() => {
      handleQuery({});
    });

    //查询电子书
    const handleQuery = (params: any) => {
      axios.get("/ebook/list").then((response) => {
        ebookslist.value = response.data.content.list;
      });
    }

    const actions: Record<string, string>[] = [
      { type: 'StarOutlined', text: '156' },
      { type: 'LikeOutlined', text: '156' },
      { type: 'MessageOutlined', text: '2' },
    ];

    return {
      pagination,
      actions,
      ebookslist,
    }
  }
});
</script>

<template>
  <div class="container">
    <h1>Список смартфонів</h1>

    <div class="controls">
      <div class="search-input">
        <label for="searchModel">Пошук:</label>
        <input type="text" id="searchModel" v-model="searchQuery" placeholder="Введіть модель">
      </div>

      <div class="brand-filter">
        <label for="brandSelect">Бренд:</label>
        <select id="brandSelect" v-model="selectedBrand">
          <option value="">Всі</option>
          <option v-for="brand in uniqueBrands" :key="brand" :value="brand">{{ brand }}</option>
        </select>
      </div>

      <div class="sort-order">
        <label for="sortOrder">Сортування:</label>
        <select id="sortOrder" v-model="sortOrder">
          <option value="asc">Від дешевих</option>
          <option value="desc">Від дорогих</option>
        </select>
      </div>
    </div>

    <div class="smartphones-grid">
      <div v-for="phone in filteredAndSortedSmartphones" :key="phone.name" class="phone-card">
        <img :src="getImagePath(phone.image)" :alt="phone.name" class="phone-image">
        <div class="phone-info">
          <h3>{{ phone.brand }} — {{ phone.name }}</h3>
          <p class="price">Ціна: ${{ phone.price.toFixed(2) }}</p>
        </div>
      </div>
    </div>

    <p v-if="filteredAndSortedSmartphones.length === 0">Немає смартфонів, що відповідають вашим критеріям.</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      smartphones: [],
      searchQuery: '',
      selectedBrand: '',
      sortOrder: 'asc' // 'asc' for ascending (cheap to expensive), 'desc' for descending
    };
  },
  computed: {
    uniqueBrands() {
      // Отримуємо унікальні бренди для фільтрації
      // Забезпечуємо, що повертаємо лише унікальні значення
      return [...new Set(this.smartphones.map(phone => phone.brand))];
    },
    filteredAndSortedSmartphones() {
      let tempSmartphones = [...this.smartphones]; // Працюємо з копією

      // 1. Фільтрація за пошуковим запитом
      if (this.searchQuery) {
        const query = this.searchQuery.toLowerCase();
        tempSmartphones = tempSmartphones.filter(phone =>
          phone.name.toLowerCase().includes(query) ||
          phone.brand.toLowerCase().includes(query)
        );
      }

      // 2. Фільтрація за брендом
      if (this.selectedBrand) {
        tempSmartphones = tempSmartphones.filter(phone => phone.brand === this.selectedBrand);
      }

      // 3. Сортування
      tempSmartphones.sort((a, b) => {
        if (this.sortOrder === 'asc') {
          return a.price - b.price;
        } else {
          return b.price - a.price;
        }
      });

      return tempSmartphones;
    }
  },
  methods: {
    getImagePath(imageName) {
      // *** ЗМІНЕНО: Тепер шлях включає '/Lab_2_DP/' ***
      return `/Lab_2_DP/assets/${imageName}`;
    }
  },
  async mounted() {
    try {
      // *** ЗМІНЕНО: Тепер запит включає '/Lab_2_DP/' ***
      const response = await fetch('/Lab_2_DP/api/smartphones');
      this.smartphones = await response.json();
    } catch (error) {
      console.error("Помилка завантаження даних:", error);
    }
  }
};
</script>

<style>
/* Загальні стилі для body */
body {
  font-family: Arial, sans-serif;
  background-color: #f4f4f4;
  margin: 0;
  padding: 0;
}

/* Стилі для контейнера */
.container {
  max-width: 960px;
  margin: 20px auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  color: #333;
  margin-bottom: 30px;
}

/* Стилі для елементів управління (пошук, фільтр, сортування) */
.controls {
  display: flex;
  justify-content: space-between;
  align-items: flex-end; /* Вирівнювання елементів по нижньому краю */
  margin-bottom: 30px;
  gap: 20px; /* Відстань між елементами управління */
  flex-wrap: wrap; /* Дозволяє елементам переноситися на новий рядок на малих екранах */
}

.controls div {
  flex: 1; /* Елементи займають рівну ширину */
  min-width: 150px; /* Мінімальна ширина для запобігання надто сильного стиснення */
}

.controls label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
}

.controls input[type="text"],
.controls select {
  width: 100%;
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1em;
  box-sizing: border-box; /* Важливо для правильного розміру (включає padding і border) */
}

/* Стилі для сітки смартфонів */
.smartphones-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); /* Адаптивна сітка */
  gap: 25px; /* Відстань між картками */
}

/* Стилі для окремої картки смартфона */
.phone-card {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 15px;
  background-color: #fff;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.08);
  display: flex;
  flex-direction: column;
  align-items: center; /* Центрування вмісту картки */
  text-align: center;
  transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out; /* Плавний перехід при наведенні */
}

.phone-card:hover {
  transform: translateY(-5px); /* Злегка піднімаємо картку при наведенні */
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* Збільшуємо тінь */
}

.phone-image {
  width: 150px; /* Збільшимо розмір зображення */
  height: 150px;
  object-fit: contain; /* Зберігає пропорції зображення */
  margin-bottom: 15px;
}

.phone-info h3 {
  font-size: 1.2em;
  color: #333;
  margin-top: 0;
  margin-bottom: 10px;
  min-height: 40px; /* Щоб назви займали однаковий простір */
  display: flex;
  align-items: center;
  justify-content: center;
}

.phone-info .description {
  font-size: 0.9em;
  color: #777;
  margin-bottom: 10px;
}

.phone-info .price {
  font-size: 1.3em;
  color: #28a745; /* Зелений для ціни */
  font-weight: bold;
}

/* Стилі для повідомлення про відсутність результатів */
p {
  text-align: center;
  margin-top: 30px;
  color: #666;
}
</style>
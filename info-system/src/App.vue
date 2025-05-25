<template>
  <div class="container">
    <h1>Список смартфонів</h1>

    <div class="form-container">
      <h2>{{ isEditing ? 'Редагувати смартфон' : 'Додати новий смартфон' }}</h2>
      <form @submit.prevent="submitForm">
        <input type="hidden" v-model="currentSmartphone.id">

        <label for="name">Назва моделі:</label>
        <input type="text" id="name" v-model="currentSmartphone.name" placeholder="Назва моделі" required>

        <label for="brand">Бренд:</label>
        <input type="text" id="brand" v-model="currentSmartphone.brand" placeholder="Бренд" required>

        <label for="image">Зображення (назва файлу):</label>
        <input type="text" id="image" v-model="currentSmartphone.image" placeholder="Назва файлу (e.g. s23.jpg)">

        <label for="description">Опис:</label>
        <textarea id="description" v-model="currentSmartphone.description" placeholder="Опис"></textarea>

        <label for="price">Ціна:</label>
        <input type="number" id="price" v-model="currentSmartphone.price" placeholder="Ціна" step="0.01" required>

        <div class="form-buttons">
          <button type="submit">{{ isEditing ? 'Зберегти зміни' : 'Додати смартфон' }}</button>
          <button type="button" @click="cancelEdit" v-if="isEditing" class="cancel-button">Скасувати</button>
        </div>
      </form>
    </div>

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
      <div v-for="phone in filteredAndSortedSmartphones" :key="phone.id" class="phone-card">
        <img :src="getImagePath(phone.image)" :alt="phone.name" class="phone-image">
        <div class="phone-info">
          <h3>{{ phone.brand }} — {{ phone.name }}</h3>
          <p class="price">Ціна: ${{ phone.price ? phone.price.toFixed(2) : '0.00' }}</p>
          <p class="description">{{ phone.description }}</p>
          <div class="actions">
            <button @click="startEdit(phone)">Редагувати</button>
            <button @click="deleteSmartphone(phone.id)" class="delete-button">Видалити</button>
          </div>
        </div>
      </div>
    </div>

    <p v-if="filteredAndSortedSmartphones.length === 0" class="no-results">
      Немає смартфонів, що відповідають вашим критеріям.
    </p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      smartphones: [],
      searchQuery: '',
      selectedBrand: '',
      sortOrder: 'asc',
      currentSmartphone: { // Об'єкт для форми додавання/редагування
        id: null,
        name: '',
        image: '',
        description: '',
        price: 0,
        brand: ''
      },
      isEditing: false // Прапорець для режиму редагування
    };
  },
  computed: {
    uniqueBrands() {
      const brands = [...new Set(this.smartphones.map(phone => phone.brand))];
      return brands.filter(brand => brand !== undefined && brand !== null && brand !== ''); // Фільтруємо пусті бренди
    },
    filteredAndSortedSmartphones() {
      let tempSmartphones = [...this.smartphones];

      if (this.searchQuery) {
        const query = this.searchQuery.toLowerCase();
        tempSmartphones = tempSmartphones.filter(phone =>
          (phone.name && phone.name.toLowerCase().includes(query)) ||
          (phone.brand && phone.brand.toLowerCase().includes(query))
        );
      }

      if (this.selectedBrand) {
        tempSmartphones = tempSmartphones.filter(phone => phone.brand === this.selectedBrand);
      }

      tempSmartphones.sort((a, b) => {
        const priceA = a.price || 0; // Забезпечуємо дефолтне значення
        const priceB = b.price || 0; // Забезпечуємо дефолтне значення

        if (this.sortOrder === 'asc') {
          return priceA - priceB;
        } else {
          return priceB - priceA;
        }
      });

      return tempSmartphones;
    }
  },
  methods: {
    getImagePath(imageName) {
      // Перевіряємо, чи image існує, щоб уникнути помилок шляху
      if (imageName) {
          return `/Lab_2_DP/assets/${imageName}`;
      }
      return ''; // Повертаємо пустий рядок, якщо зображення немає
    },
    async fetchSmartphones() {
      try {
        // Шлях до API-сервлета
        const response = await fetch('/Lab_2_DP/api/smartphones');
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        this.smartphones = await response.json();
      } catch (error) {
        console.error("Помилка завантаження даних:", error);
        // Можна додати сповіщення користувачеві
        alert("Не вдалося завантажити смартфони. Перевірте з'єднання та бекенд.");
      }
    },
    async submitForm() {
      // Проста валідація
      if (!this.currentSmartphone.name || !this.currentSmartphone.brand || this.currentSmartphone.price <= 0) {
        alert('Будь ласка, заповніть обов\'язкові поля (Назва, Бренд, Ціна > 0).');
        return;
      }

      const method = this.isEditing ? 'PUT' : 'POST';
      const url = this.isEditing
        ? `/Lab_2_DP/api/smartphones/${this.currentSmartphone.id}`
        : '/Lab_2_DP/api/smartphones';

      try {
        const response = await fetch(url, {
          method: method,
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.currentSmartphone)
        });

        if (response.ok) {
          await this.fetchSmartphones(); // Оновлюємо список після успішної операції
          this.resetForm(); // Очищуємо форму
        } else {
          const errorData = await response.json();
          console.error("Помилка операції:", errorData.error);
          alert(`Помилка: ${errorData.error || response.statusText}`);
        }
      } catch (error) {
        console.error("Помилка мережі або запиту:", error);
        alert("Помилка мережі або запиту.");
      }
    },
    startEdit(phone) {
      // Робимо копію об'єкта, щоб не змінювати дані в списку напряму до збереження
      this.currentSmartphone = { ...phone };
      this.isEditing = true;
    },
    cancelEdit() {
      this.resetForm();
    },
    resetForm() {
      this.currentSmartphone = {
        id: null,
        name: '',
        image: '',
        description: '',
        price: 0,
        brand: ''
      };
      this.isEditing = false;
    },
    async deleteSmartphone(id) {
      if (!confirm('Ви впевнені, що хочете видалити цей смартфон?')) {
        return;
      }
      try {
        const response = await fetch(`/Lab_2_DP/api/smartphones/${id}`, {
          method: 'DELETE'
        });

        if (response.ok) {
          await this.fetchSmartphones(); // Оновлюємо список після успішного видалення
        } else {
          const errorData = await response.json();
          console.error("Помилка видалення:", errorData.error);
          alert(`Помилка видалення: ${errorData.error || response.statusText}`);
        }
      } catch (error) {
        console.error("Помилка мережі при видаленні:", error);
        alert("Помилка мережі при видаленні.");
      }
    }
  },
  async mounted() {
    // Завантажуємо смартфони при завантаженні компонента
    await this.fetchSmartphones();
  }
};
</script>

<style>
/* Додайте або оновіть стилі відповідно до ваших потреб */
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

h1 {
  text-align: center;
  color: #333;
  margin-bottom: 30px;
}

.form-container {
  background-color: #f9f9f9;
  padding: 25px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  margin-bottom: 40px;
}

.form-container h2 {
  text-align: center;
  margin-bottom: 25px;
  color: #555;
}

.form-container form {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 15px;
}

.form-container label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #666;
}

.form-container input[type="text"],
.form-container input[type="number"],
.form-container textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1em;
  box-sizing: border-box; /* Важливо для правильного розміру */
}

.form-container textarea {
  grid-column: span 2; /* Опис займає дві колонки */
  resize: vertical; /* Дозволити вертикальну зміну розміру */
  min-height: 80px;
}

.form-buttons {
    grid-column: span 2; /* Кнопки займають дві колонки */
    display: flex;
    justify-content: center;
    gap: 15px;
    margin-top: 15px;
}

.form-container button {
  padding: 12px 25px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1.1em;
  font-weight: bold;
  color: white;
  transition: background-color 0.3s ease;
}

.form-container button[type="submit"] {
  background-color: #28a745; /* Зелений для додавання/збереження */
}

.form-container button[type="submit"]:hover {
  background-color: #218838;
}

.form-container .cancel-button {
  background-color: #6c757d; /* Сірий для скасування */
}

.form-container .cancel-button:hover {
  background-color: #5a6268;
}

.controls {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
  padding: 15px;
  background-color: #e2f0cb;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
}

.controls label {
  margin-right: 10px;
  font-weight: bold;
  color: #4a4a4a;
}

.controls input[type="text"],
.controls select {
  padding: 8px 12px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 0.95em;
}

.smartphones-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 30px;
}

.phone-card {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  display: flex;
  flex-direction: column;
  transition: transform 0.2s ease-in-out;
}

.phone-card:hover {
  transform: translateY(-5px);
}

.phone-image {
  width: 100%;
  height: 200px;
  object-fit: contain; /* Зображення вміщається без обрізки */
  padding: 10px; /* Додаємо відступи */
  background-color: #eee;
}

.phone-info {
  padding: 20px;
  display: flex;
  flex-direction: column;
  flex-grow: 1; /* Дозволяє інформації займати весь доступний простір */
}

.phone-info h3 {
  margin-top: 0;
  margin-bottom: 10px;
  color: #333;
  font-size: 1.3em;
}

.phone-info .price {
  font-weight: bold;
  color: #e67e22; /* Помаранчевий для ціни */
  font-size: 1.2em;
  margin-bottom: 10px;
}

.phone-info .description {
  font-size: 0.9em;
  color: #666;
  line-height: 1.5;
  flex-grow: 1; /* Дозволяє опису займати весь доступний простір */
}

.actions {
  margin-top: 15px;
  display: flex;
  justify-content: flex-end; /* Вирівнюємо кнопки праворуч */
  gap: 10px;
}

.actions button {
  padding: 8px 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 0.9em;
  font-weight: bold;
  transition: background-color 0.3s ease;
}

.actions button:first-child { /* Кнопка "Редагувати" */
  background-color: #007bff; /* Синій */
  color: white;
}

.actions button:first-child:hover {
  background-color: #0056b3;
}

.actions .delete-button { /* Кнопка "Видалити" */
  background-color: #dc3545; /* Червоний */
  color: white;
}

.actions .delete-button:hover {
  background-color: #c82333;
}

.no-results {
  text-align: center;
  grid-column: 1 / -1; /* Повідомлення займає всю ширину сітки */
  color: #888;
  font-size: 1.2em;
  margin-top: 50px;
}

/* Адаптивність */
@media (max-width: 768px) {
  .controls {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }

  .form-container form {
    grid-template-columns: 1fr; /* Одна колонка для форм на мобільних */
  }

  .form-container textarea {
    grid-column: span 1;
  }
}
</style>
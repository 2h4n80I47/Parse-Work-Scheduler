
	Документация 
  
  
  
  
	Класс WorkTimeParser
	
	Класс предоставляет метод для обработки расписания работы специалистов, представленного в виде двумерного массива строк. Метод разбивает время работы каждого специалиста на диапазоны по дням недели и объединяет смежные диапазоны в один.
	
	Методы:
	
	prepareWorkTime(String[][] grafic): String[][]
	Осуществляет обработку расписания работы специалистов. Принимает на вход двумерный массив строк grafic, представляющий собой расписание работы специалистов. Массив имеет следующую структуру: первый столбец - ФИО специалиста, остальные столбцы - время работы специалиста по дням недели (время указывается в формате "HH:MM-HH:MM"). Метод возвращает двумерный массив строк, содержащий обработанные записи. Массив имеет следующую структуру: первый столбец - ФИО специалиста, остальные столбцы - объединенное время работы специалиста по дням недели (время указывается в формате "hh:mm-hh:mm").
	
	mergeRanges(List<String> ranges): String
	Метод объединяет смежные диапазоны в один диапазон. Принимает на вход список строк ranges, представляющий собой диапазоны времени работы специалиста. Строки имеют формат "HH:MM-HH:MM". Метод возвращает строку, представляющую объединенный диапазон времени (в формате "HH:MM-HH:MM").
	
	Примечания:
	
	    Если в диапазоне нет времени работы, то метод mergeRanges возвращает строку "".
	    Для сортировки записей используется метод Collections.sort с компаратором, который сравнивает записи по дню недели.
	    Для хранения рабочего времени каждого специалиста используется структура Map<String, List<String[]>>, где ключ - ФИО специалиста, значение - список диапазонов времени работы специалиста по дням недели.


UML Диаграмма
	UML Диаграмма
					
     +------------------+            +---------------------+
     | WorkTimeParser   |            |       Ideone        |
     +------------------+            +---------------------+
     | prepareWorkTime()|            | main()              |
     | -mergeRanges()   |            +---------------------+
     +------------------+
              |
              |
              |
     +------------------+
     |     Map         |
     +------------------+
     | specialistTime   |
     +------------------+
              |
              |
              |
     +------------------+
     |   List<String[]> |
     +------------------+
     | specialistSchedule|
     +------------------+


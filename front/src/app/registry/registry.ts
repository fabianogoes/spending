import { Type } from '../type/type';
import { Category } from '../category/category';
export class Registry {

    id: string;
    date: string;
    description: string;
    type: Type = new Type();
    value: number;
    category: Category = new Category();

    constructor(values: Object = {}) {
      // Constructor initialization
      Object.assign(this, values);
    }
}

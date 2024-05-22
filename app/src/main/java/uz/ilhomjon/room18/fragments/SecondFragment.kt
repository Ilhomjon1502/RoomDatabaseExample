package uz.ilhomjon.room18.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.ilhomjon.room18.R
import uz.ilhomjon.room18.databinding.FragmentSecondBinding
import uz.ilhomjon.room18.db.AppDatabase
import uz.ilhomjon.room18.models.User

class SecondFragment : Fragment() {
    private val binding by lazy { FragmentSecondBinding.inflate(layoutInflater) }
    lateinit var appDatabase: AppDatabase
    var user:User? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        appDatabase = AppDatabase.getInstance(binding.root.context)

        try {
            user= arguments?.getSerializable("keyUser") as User
            editUser()
        }catch (e:Exception){
            addUser()
        }


        return binding.root
    }

    fun addUser(){
        binding.apply {
            btnSave.setOnClickListener {
                val user = User(
                    edtName.text.toString(),
                    edtNumber.text.toString()
                )
                appDatabase.myDao().addUser(user)
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }

    fun editUser(){
        binding.apply {
            edtName.setText(user?.name)
            edtNumber.setText(user?.number)

            btnSave.setOnClickListener {
                user?.name = edtName.text.toString()
                user?.number = edtNumber.text.toString()
                appDatabase.myDao().editUser(user!!)
                findNavController().popBackStack()
            }
        }
    }
}
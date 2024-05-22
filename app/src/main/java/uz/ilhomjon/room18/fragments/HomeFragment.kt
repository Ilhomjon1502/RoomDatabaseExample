package uz.ilhomjon.room18.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.ilhomjon.room18.R
import uz.ilhomjon.room18.adapters.RvAdapter
import uz.ilhomjon.room18.databinding.FragmentHomeBinding
import uz.ilhomjon.room18.db.AppDatabase
import uz.ilhomjon.room18.models.User

class HomeFragment : Fragment(), RvAdapter.RvAction {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    lateinit var appDatabase: AppDatabase
    lateinit var rvAdapter: RvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.apply {
            appDatabase = AppDatabase.getInstance(requireContext())
            rvAdapter = RvAdapter(appDatabase.myDao().getAllUsers() as ArrayList<User>, this@HomeFragment)
            rv.adapter = rvAdapter


            btnAdd.setOnClickListener {
                findNavController().navigate(R.id.secondFragment)
            }
        }
        return binding.root
    }

    override fun moreClick(user: User, position: Int, imageView: ImageView) {
        val popupMenu = PopupMenu(requireContext(), imageView)
        popupMenu.inflate(R.menu.my_menu)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_delete->{
                    appDatabase.myDao().deleteUser(user)
                    rvAdapter.list.removeAt(position)
                    rvAdapter.notifyItemRemoved(position)
                }
                R.id.menu_edit->{
                    findNavController().navigate(R.id.secondFragment, bundleOf("keyUser" to user))
                }
            }

            true
        }

        popupMenu.show()
    }
}